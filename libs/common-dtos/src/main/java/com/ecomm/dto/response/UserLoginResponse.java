package com.ecomm.dto.response;

public record UserLoginResponse(String token, long expirationTime) {
}
