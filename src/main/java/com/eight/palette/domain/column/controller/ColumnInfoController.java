package com.eight.palette.domain.column.controller;

import com.eight.palette.domain.column.dto.ColumnInfoRequestDto;
import com.eight.palette.domain.column.service.ColumnInfoService;
import com.eight.palette.domain.user.entity.UserDetailsImpl;
import com.eight.palette.global.dto.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ColumnInfoController {

    private final ColumnInfoService columnInfoService;

    @PostMapping("/manager/boards/{boardId}/columns")
    public ResponseEntity<MessageResponse> createColumn(@PathVariable(name = "boardId") Long boardId,
                                                        @Valid @RequestBody ColumnInfoRequestDto columnInfoRequestDto,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        columnInfoService.createColumn(boardId, columnInfoRequestDto, userDetails.getUser());

        return ResponseEntity.status(201).body(new MessageResponse(201, "컬럼 생성 성공 \uD83C\uDF89"));

    }

    @DeleteMapping("/manager/boards/{boardId}/columns/{columnsId}")
    public ResponseEntity<MessageResponse> deleteColumn(@PathVariable(name = "boardId") Long boardId,
                                                        @PathVariable(name = "columnsId") Long columnInfoId,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        columnInfoService.deleteColumn(boardId, columnInfoId, userDetails.getUser());

        return ResponseEntity.status(200).body(new MessageResponse(200, "컬럼 삭제 성공 \uD83C\uDF89"));

    }

    @PostMapping("/manager/boards/{boardId}/columns/{columnsId}/order")
    public ResponseEntity<MessageResponse> moveColumn(@PathVariable(name = "boardId") Long boardId,
                                                      @PathVariable(name = "columnsId") Long columnInfoId,
                                                      @RequestParam(name = "newOrder") Integer newOrder,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {

        columnInfoService.moveColumn(boardId, columnInfoId, newOrder, userDetails.getUser());

        return ResponseEntity.status(200).body(new MessageResponse(200, "컬럼 순서 변경 성공 \uD83C\uDF89"));

    }


}