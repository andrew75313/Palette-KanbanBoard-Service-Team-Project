package com.eight.palette.domain.card.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CardRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    private String content;
    private String deadLineDate;
    private String worker;
}
