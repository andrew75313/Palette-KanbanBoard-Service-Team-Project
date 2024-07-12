package com.eight.palette.domain.column.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ColumnInfoRequestDto {

    @NotBlank(message = "상태 이름을 입력해주세요.")
    private String statusName;

}
