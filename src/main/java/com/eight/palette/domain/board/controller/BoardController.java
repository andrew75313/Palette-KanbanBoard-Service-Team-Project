package com.eight.palette.domain.board.controller;

import com.eight.palette.domain.board.dto.BoardRequestDto;
import com.eight.palette.domain.board.dto.BoardResponseDto;
import com.eight.palette.domain.board.service.BoardService;
import com.eight.palette.domain.invite.dto.InviteRequestDto;
import com.eight.palette.domain.user.entity.UserDetailsImpl;
import com.eight.palette.global.dto.DataResponse;
import com.eight.palette.global.dto.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

    @PostMapping("/boards")
    public ResponseEntity<DataResponse<BoardResponseDto>> createBoard( @Valid @RequestBody BoardRequestDto requestDto,
                                                                       @AuthenticationPrincipal UserDetailsImpl userPrincipal)
    {

        BoardResponseDto responseDto = boardService.createBoard(userPrincipal.getUser(), requestDto);
        return ResponseEntity.ok(new DataResponse<>(HttpStatus.CREATED.value(),"보드 생성 성공 📝", responseDto ));

    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<DataResponse<BoardResponseDto>> updateBoard( @Valid @RequestBody BoardRequestDto requestDto,
                                                                       @AuthenticationPrincipal UserDetailsImpl userPrincipal,
                                                                       @PathVariable Long boardId)
    {

        BoardResponseDto responseDto = boardService.updateBoard(userPrincipal.getUser(), boardId, requestDto);
        return ResponseEntity.ok(new DataResponse<>(HttpStatus.OK.value(),"보드 수정 성공 📝", responseDto));

    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<MessageResponse> deleteBoard( @AuthenticationPrincipal UserDetailsImpl userPrincipal,
                                                        @PathVariable Long boardId)
    {

        boardService.deleteBoard(userPrincipal.getUser(), boardId);
        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(),"보드 삭제 성공 📝"));

    }

    @GetMapping("/check/boards")
    public ResponseEntity<DataResponse<Page<BoardResponseDto>>> getBoard(@RequestParam(defaultValue = "1") int page,
                                                                         @AuthenticationPrincipal UserDetailsImpl userPrincipal) {

        int defaultSize = 5;
        Page<BoardResponseDto> responseDto = boardService.getBoard(userPrincipal.getUser(), page - 1, defaultSize);
        return ResponseEntity.ok(new DataResponse<>(HttpStatus.OK.value(), "보드 "+page+"번 페이지 조회 성공 📝", responseDto));
    }


    @PostMapping("boards/{boardId}/invite")
    public ResponseEntity<MessageResponse> inviteBoard( @AuthenticationPrincipal UserDetailsImpl userPrincipal,
                                                        @PathVariable Long boardId,
                                                        @Valid @RequestBody InviteRequestDto requestDto)
    {

        boardService.inviteBoard(userPrincipal.getUser(), boardId, requestDto.getInvitedUserId());
        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), "사용자 초대 성공 👨‍👨‍👧‍👦"));

    }

}
