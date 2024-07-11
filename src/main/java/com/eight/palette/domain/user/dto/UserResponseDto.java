package com.eight.palette.domain.user.dto;

import com.eight.palette.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final String username;
    private final String password;

    public UserResponseDto(User user) {

        this.username = user.getUsername();
        this.password = user.getPassword();

    }

}
