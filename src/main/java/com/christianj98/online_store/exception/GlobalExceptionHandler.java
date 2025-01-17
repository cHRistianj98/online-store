package com.christianj98.online_store.exception;

import com.christianj98.online_store.dto.exception.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorDetails handleBadCredentialsException(BadCredentialsException exception) {
        return ErrorDetails.builder()
                .exceptionClass(exception.getClass().getSimpleName())
                .httpStatus(HttpStatus.FORBIDDEN)
                .errorMessage(exception.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ErrorDetails.builder()
                .exceptionClass(exception.getClass().getSimpleName())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errorMessage(exception.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails handleGeneralException(Exception exception) {
        return ErrorDetails.builder()
                .exceptionClass(exception.getClass().getName())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(exception.getMessage())
                .build();
    }
}