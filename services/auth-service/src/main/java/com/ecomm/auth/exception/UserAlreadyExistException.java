package com.ecomm.auth.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends AppException {
    public UserAlreadyExistException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
