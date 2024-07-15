package com.eight.palette.domain.card.dto;

import com.eight.palette.domain.card.entity.Card;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CardResponseDto {

    private final Long cardId;
    private final String title;
    private final String content;
    private final String deadLineDate;
    private final String worker;
    private final Integer position;
    private final Long columnId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CardResponseDto(Card card) {
        this.cardId = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.deadLineDate = card.getDeadLineDate();
        this.worker = card.getWorker();
        this.columnId = card.getColumnInfo().getId();
        this.createdAt = card.getCreatedAt();
        this.modifiedAt = card.getModifiedAt();
        this.position = card.getPosition();
    }
}



