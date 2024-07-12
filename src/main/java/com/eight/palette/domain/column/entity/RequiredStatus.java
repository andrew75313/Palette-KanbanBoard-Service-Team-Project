package com.eight.palette.domain.column.entity;

public enum RequiredStatus {

    UPCOMING("시작 전"),
    IN_PROGRESS("진행 중"),
    DONE("완료");

    private final String ColumnStatus;

    RequiredStatus(String columnStatus) {
        this.ColumnStatus = columnStatus;
    }

    public String getColumnStatus() {
        return ColumnStatus;
    }
}
