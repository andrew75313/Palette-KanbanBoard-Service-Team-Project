package com.eight.palette.domain.board.dto;

import com.eight.palette.domain.board.entity.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private Long boardId;
    private String username;
    private String title;
    private String intro;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    public BoardResponseDto(Board board)
    {
        this.boardId = board.getId();
        this.username = board.getUser().getUsername();
        this.title = board.getTitle();
        this.intro = board.getIntro();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}
