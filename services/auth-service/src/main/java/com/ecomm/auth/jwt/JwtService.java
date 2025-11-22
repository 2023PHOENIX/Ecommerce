package com.ecomm.auth.jwt;

import com.ecomm.auth.jwt.strategy.JwtStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtStrategy jwtStrategy;


    public JwtService(@Qualifier("HS256") JwtStrategy jwtStrategy) {
        this.jwtStrategy = jwtStrategy;
    }

    public String generateJwtToken(String subject){
        return jwtStrategy.generateToken(subject);
    }

    public String extractEmail(String token){
        return jwtStrategy.extractSubject(token);
    }

    public Boolean isValidToken(String token){
        return jwtStrategy.validateToken(token);
    }


}
