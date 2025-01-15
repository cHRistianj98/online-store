package com.christianj98.online_store.dto.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ErrorDetails(
        HttpStatus httpStatus,
        String exceptionClass,
        String errorMessage
) {
}
