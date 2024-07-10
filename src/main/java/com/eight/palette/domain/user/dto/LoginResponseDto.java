package com.eight.palette.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String token;

    private String username;

    public LoginResponseDto(String token, String username) {
        this.token = token;
        this.username = username;
    }

}
