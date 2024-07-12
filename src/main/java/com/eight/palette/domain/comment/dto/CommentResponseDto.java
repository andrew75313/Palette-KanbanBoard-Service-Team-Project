package com.eight.palette.domain.comment.dto;

import com.eight.palette.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String comment;

    public CommentResponseDto(Comment comment) {

        this.id = comment.getId();
        this.comment = comment.getComment();
    }
}
