package com.eight.palette.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank(message = "댓글을 입력해주세요.")
    @Size(max = 50, message = "댓글은 최대 50자 이하여야 합니다.")
    private String comment;
}
