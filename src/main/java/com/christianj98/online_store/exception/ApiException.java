package com.christianj98.online_store.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
