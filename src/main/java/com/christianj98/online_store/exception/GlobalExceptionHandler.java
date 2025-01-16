package com.christianj98.online_store.exception;

import com.christianj98.online_store.dto.exception.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorDetails handleAccessDeniedException(BadCredentialsException exception) {
        return ErrorDetails.builder()
                .exceptionClass(exception.getClass().getSimpleName())
                .httpStatus(HttpStatus.FORBIDDEN)
                .errorMessage(exception.getMessage())
                .build();
    }
}