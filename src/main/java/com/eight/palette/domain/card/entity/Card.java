package com.eight.palette.domain.card.entity;

import com.eight.palette.domain.board.entity.Board;
import com.eight.palette.domain.column.entity.ColumnInfo;
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
public class Card extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Column
    private String deadLineDate;

    @Column
    private String worker;

    @Column
    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY)
    private ColumnInfo columnInfo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Card.Status status;

    public enum Status {
        ACTIVE,
        DELETED;
    }
}
