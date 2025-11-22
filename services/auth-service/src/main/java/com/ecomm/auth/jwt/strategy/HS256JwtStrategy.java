package com.ecomm.auth.jwt.strategy;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
@Qualifier("HS256")
@Slf4j
public class HS256JwtStrategy implements JwtStrategy {

    private final String secret;
    private final Long expiration;
    private final SecretKey signingKey;

    /**
     * Initializes the component using constructor injection for configuration values.
     * This avoids the need for @PostConstruct and ensures the stable SecretKey
     * is created immediately upon bean instantiation.
     */
    public HS256JwtStrategy(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration}") Long expiration) {

        this.secret = secret;
        this.expiration = expiration;

        // Key initialization logic is performed directly in the constructor
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        log.info("HS256 Strategy initialized and stable key generated from constructor.");
    }

    @Override
    public String generateToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(this.signingKey)
                .compact();
    }

    @Override
    public String extractSubject(String token) {
        return Jwts.parser().verifyWith(this.signingKey).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    @Override
    public Boolean validateToken(String token) {
        try {
            String subject = extractSubject(token);
            log.atInfo().log("token for this request :: {}", token);
            log.atInfo().log("subject of the token :: {}", subject);
            return true;
        } catch (SignatureException ex) {
            log.atWarn().log("signate issue :: {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.atWarn().log("Jwt Expiration :: {}", ex.getMessage());
        } catch (Exception ex) {
            log.atError().log("{}: \n there is issue with the jwt token ::{}", ex.getCause(), ex.getMessage());
            return false;
        }
        return true;
    }
}
