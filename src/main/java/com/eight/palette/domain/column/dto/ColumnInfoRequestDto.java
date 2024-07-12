package com.eight.palette.domain.column.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ColumnInfoRequestDto {

    @NotBlank(message = "상태 이름을 입력해주세요.")
    @Size(max = 20, message = "상태 이름은 최대 20자 이하여야 합니다.")
    private String statusName;

}
