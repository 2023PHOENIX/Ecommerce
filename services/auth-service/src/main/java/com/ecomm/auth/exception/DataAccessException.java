package com.ecomm.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DataAccessException extends AppException {
    public DataAccessException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
