package com.eight.palette.global.dto;

import lombok.Getter;

@Getter
public class DataResponse<T> {

    private int statusCode;
    private String message;
    private T data;

    public DataResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

}
