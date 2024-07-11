package com.eight.palette.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String message;

    public CustomException(ErrorCode errorCode) {
        this.statusCode = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }
}
