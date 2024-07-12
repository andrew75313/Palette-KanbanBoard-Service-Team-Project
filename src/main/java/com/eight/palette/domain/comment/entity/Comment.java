package com.eight.palette.domain.comment.entity;

import com.eight.palette.domain.card.entity.Card;
import com.eight.palette.domain.comment.dto.CommentRequestDto;
import com.eight.palette.domain.user.entity.User;
import com.eight.palette.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Comment(CommentRequestDto commentRequestDto, Card card, User user) {

        this.comment = commentRequestDto.getComment();
        this.card = card;
        this.user = user;
    }

}
