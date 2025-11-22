package com.ecomm.auth.exception;

import com.ecomm.dto.exception.ApiResponseDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleValidationException(
            MethodArgumentNotValidException ex,
            WebRequest request
    ){
        String message = Objects.requireNonNull(ex.getFieldError()).getDefaultMessage();

        return new ResponseEntity<>(generateErrorResponse(HttpStatus.BAD_REQUEST,message,request.getDescription(false)), null,HttpStatus.BAD_REQUEST);
    }


   @ExceptionHandler(AppException.class)
   public ResponseEntity<ApiResponseDTO<Object>> handleAppException(AppException ex,WebRequest request){
        return new ResponseEntity<>(generateErrorResponse(ex.getStatusCode(),ex.getMessage(),request.getDescription(false)),ex.getStatusCode());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleDatabaseErrors(AppException dataAccessException,WebRequest request){
        return new ResponseEntity<>(generateErrorResponse(dataAccessException.getStatusCode(),dataAccessException.getMessage(),request.getDescription(false)), dataAccessException.getStatusCode());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleInvalidPasswordException(AppException invalidPasswordException,WebRequest request){
        return new ResponseEntity<>(generateErrorResponse(invalidPasswordException.getStatusCode(),invalidPasswordException.getMessage(),request.getDescription(false)), invalidPasswordException.getStatusCode());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleUserAlreadyExistException(AppException userAlreadyExistException,WebRequest request){
        return new ResponseEntity<>(generateErrorResponse(userAlreadyExistException.getStatusCode(), userAlreadyExistException.getMessage(),request.getDescription(false)),userAlreadyExistException.getStatusCode());
    }

    ApiResponseDTO<Object> generateErrorResponse(HttpStatus statusCode,String errorMessage,String path){
        return new ApiResponseDTO<Object>(LocalDateTime.now(), statusCode.value(),errorMessage,path,null);
    }


}

