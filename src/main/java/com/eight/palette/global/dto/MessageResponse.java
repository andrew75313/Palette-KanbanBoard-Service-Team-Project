package com.eight.palette.global.dto;

import lombok.Getter;

@Getter
public class MessageResponse {

    private int statusCode;
    private String message;

    public MessageResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}