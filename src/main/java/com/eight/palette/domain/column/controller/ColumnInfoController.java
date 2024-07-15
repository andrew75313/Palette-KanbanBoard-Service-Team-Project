package com.eight.palette.domain.column.controller;

import com.eight.palette.domain.column.dto.ColumnInfoRequestDto;
import com.eight.palette.domain.column.dto.ColumnInfoResponseDto;
import com.eight.palette.domain.column.service.ColumnInfoService;
import com.eight.palette.domain.user.entity.UserDetailsImpl;
import com.eight.palette.global.dto.DataResponse;
import com.eight.palette.global.dto.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ColumnInfoController {

    private final ColumnInfoService columnInfoService;

    public ColumnInfoController(ColumnInfoService columnInfoService) {
        this.columnInfoService = columnInfoService;
    }

    @PostMapping("/boards/{boardId}/columns")
    public ResponseEntity<DataResponse<ColumnInfoResponseDto>> createColumn(@PathVariable(name = "boardId") Long boardId,
                                                                            @Valid @RequestBody ColumnInfoRequestDto columnInfoRequestDto,
                                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        ColumnInfoResponseDto responseDto = columnInfoService.createColumn(boardId, columnInfoRequestDto, userDetails.getUser());

        return ResponseEntity.ok(new DataResponse<>(200, "컬럼 생성 성공 \uD83C\uDF89", responseDto));

    }

    @GetMapping("/boards/{boardId}/columns/{columnId}")
    public ResponseEntity<DataResponse<ColumnInfoResponseDto>> getColumn(@PathVariable(name = "boardId") Long boardId,
                                                                         @PathVariable(name = "columnId") Long columnInfoId,
                                                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {

        ColumnInfoResponseDto responseDto = columnInfoService.getColumn(boardId, columnInfoId, userDetails.getUser());

        return ResponseEntity.ok(new DataResponse<>(200, "컬럼 조회 성공 \uD83C\uDF89", responseDto));

    }

    @GetMapping("/boards/{boardId}/columns")
    public ResponseEntity<DataResponse<List<ColumnInfoResponseDto>>> getAllColumns(@PathVariable(name = "boardId") Long boardId,
                                                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<ColumnInfoResponseDto> responseDto = columnInfoService.getAllColumns(boardId, userDetails.getUser());

        return ResponseEntity.ok(new DataResponse<>(200, "컬럼 조회 성공 \uD83C\uDF89", responseDto));

    }

    @DeleteMapping("/boards/{boardId}/columns/{columnId}")
    public ResponseEntity<MessageResponse> deleteColumn(@PathVariable(name = "boardId") Long boardId,
                                                        @PathVariable(name = "columnId") Long columnInfoId,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        columnInfoService.deleteColumn(boardId, columnInfoId, userDetails.getUser());

        return ResponseEntity.ok(new MessageResponse(200, "컬럼 삭제 성공 \uD83C\uDF89"));

    }

    @PutMapping("/boards/{boardId}/columns/{columnId}/move")
    public ResponseEntity<MessageResponse> moveColumn(@PathVariable(name = "boardId") Long boardId,
                                                      @PathVariable(name = "columnId") Long columnInfoId,
                                                      @RequestParam(name = "position") Integer newPosition,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {

        columnInfoService.moveColumn(boardId, columnInfoId, newPosition, userDetails.getUser());

        return ResponseEntity.ok(new MessageResponse(200, "컬럼 순서 변경 성공 \uD83C\uDF89"));

    }

}