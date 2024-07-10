package com.eight.palette.domain.column.service;

import com.eight.palette.domain.column.repository.ColumnsRepository;
import org.springframework.stereotype.Service;

@Service
public class ColumnInfoService {

    private final ColumnsRepository columnsRepository;

    public ColumnInfoService(ColumnsRepository columnsRepository) {
        this.columnsRepository = columnsRepository;
    }

}
