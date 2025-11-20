package com.ecomm.auth.jwt.strategy;

public interface JwtStrategy {
    public String generateToken(String subject);

    public String extractSubject(String token);

    public Boolean validateToken(String token);
}
