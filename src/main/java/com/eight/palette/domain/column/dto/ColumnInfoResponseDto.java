package com.eight.palette.domain.column.dto;

import com.eight.palette.domain.column.entity.ColumnInfo;
import lombok.Getter;

@Getter
public class ColumnInfoResponseDto {

    private final Long id;
    private final String status;

    public ColumnInfoResponseDto(ColumnInfo columnInfo) {

        this.id = columnInfo.getId();
        this.status = columnInfo.getStatus();

    }
}
