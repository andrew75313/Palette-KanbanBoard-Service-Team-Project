package com.eight.palette.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // board
    USER_DIFFERENT(HttpStatus.BAD_REQUEST, "다른 사용자의 보드입니다."),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "보드를 찾을 수 없습니다.");

    // column

    // user

    // card

    private final HttpStatus status;
    private final String message;
}
