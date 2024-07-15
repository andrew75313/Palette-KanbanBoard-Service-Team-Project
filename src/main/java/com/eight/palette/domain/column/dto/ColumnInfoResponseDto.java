package com.eight.palette.domain.column.dto;

import com.eight.palette.domain.column.entity.ColumnInfo;
import lombok.Getter;

@Getter
public class ColumnInfoResponseDto {

    private final Long id;
    private final String statusName;
    private final Integer position;

    public ColumnInfoResponseDto(ColumnInfo columnInfo) {

        this.id = columnInfo.getId();
        this.statusName = columnInfo.getStatusName();
        this.position = columnInfo.getPosition();

    }

}
