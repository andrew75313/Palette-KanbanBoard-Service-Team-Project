package com.eight.palette.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RefreshTokenDto {

    @NotBlank(message = "리프레쉬 토큰을 입력해주세요.")
    private String refreshToken;

}
