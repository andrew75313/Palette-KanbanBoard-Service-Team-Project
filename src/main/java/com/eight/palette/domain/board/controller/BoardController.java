package com.eight.palette.domain.board.controller;

import com.eight.palette.domain.board.dto.BoardRequestDto;
import com.eight.palette.domain.board.dto.BoardResponseDto;
import com.eight.palette.domain.board.service.BoardService;
import com.eight.palette.domain.user.entity.UserDetailsImpl;
import com.eight.palette.global.dto.DataResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/manager/boards")
    public ResponseEntity<DataResponse<BoardResponseDto>> createBoard( @Valid @RequestBody BoardRequestDto requestDto,
                                                                       @AuthenticationPrincipal UserDetailsImpl userPrincipal)
    {

        BoardResponseDto responseDto = boardService.createBoard(userPrincipal.getUser(), requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataResponse<>(HttpStatus.CREATED.value(),"보드 생성 성공 📝", responseDto ));

    }

    @PutMapping("/manager/boards/{boardId}")
    public ResponseEntity<DataResponse<BoardResponseDto>> updateBoard( @Valid @RequestBody BoardRequestDto requestDto,
                                                                      @AuthenticationPrincipal UserDetailsImpl userPrincipal,
                                                                      @PathVariable Long boardId)
    {
        BoardResponseDto responseDto = boardService.updateBoard(userPrincipal.getUser(), boardId, requestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new DataResponse<>(HttpStatus.OK.value(),"보드 수정 성공 📝", responseDto));
    }


}
