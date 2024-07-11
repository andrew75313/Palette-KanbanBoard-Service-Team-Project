package com.eight.palette.global.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class CommonErrorResponse {
    private String msg;
    private int status;
    private String error;
    private String path;
    private LocalDateTime timestamp;
}

