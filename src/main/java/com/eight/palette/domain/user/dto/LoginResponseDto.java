package com.eight.palette.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private final String accessToken;
    private final String refreshToken;
    private final String username;

    public LoginResponseDto(String accessToken, String refreshToken, String username) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
    }

}
