package com.eight.palette.domain.comment.controller;

import com.eight.palette.domain.comment.dto.CommentRequestDto;
import com.eight.palette.domain.comment.dto.CommentResponseDto;
import com.eight.palette.domain.comment.service.CommentService;
import com.eight.palette.domain.user.entity.UserDetailsImpl;
import com.eight.palette.global.dto.DataResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/cards/{cardId}/comments")
    public ResponseEntity<DataResponse<CommentResponseDto>> createComment(@PathVariable(name = "cardId") Long cardId,
                                                                          @Valid @RequestBody CommentRequestDto commentRequestDto,
                                                                          @AuthenticationPrincipal UserDetailsImpl userDetail) {

        CommentResponseDto responseDto = commentService.createCommnet(cardId, commentRequestDto, userDetail.getUser());

        return ResponseEntity.ok(new DataResponse<>(200, "댓글 생성 성공 \uD83C\uDCCF", responseDto));

    }

    @GetMapping("/cards/{cardId}/comments")
    public ResponseEntity<DataResponse<List<CommentResponseDto>>> getComments(@PathVariable(name = "cardId") Long cardId,
                                                                              @AuthenticationPrincipal UserDetailsImpl userDetail) {

        List<CommentResponseDto> responseDtoList = commentService.getComments(cardId, userDetail.getUser());

        return ResponseEntity.ok(new DataResponse<>(200, "댓글 조회 성공 \uD83C\uDCCF", responseDtoList));

    }

}
