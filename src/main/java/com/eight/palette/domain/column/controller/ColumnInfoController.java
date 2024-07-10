package com.eight.palette.domain.column.controller;

import com.eight.palette.domain.column.service.ColumnInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ColumnInfoController {

    private final ColumnInfoService columnInfoService;

    public ColumnInfoController(ColumnInfoService columnInfoService) {
        this.columnInfoService = columnInfoService;
    }

}
