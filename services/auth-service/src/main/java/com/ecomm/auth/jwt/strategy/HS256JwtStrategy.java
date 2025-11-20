package com.ecomm.auth.jwt.strategy;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@Qualifier("HS256")
public class HS256JwtStrategy implements JwtStrategy{

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private Long expiration;

    private static SecretKey getSigningKey(){
        return Jwts.SIG.HS256.key().build();
    }

    @Override
    public String generateToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String extractSubject(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    @Override
    public Boolean validateToken(String token) {
        try {
            extractSubject(token);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
