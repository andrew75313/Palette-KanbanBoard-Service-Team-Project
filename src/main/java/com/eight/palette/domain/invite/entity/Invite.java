package com.eight.palette.domain.invite.entity;

import com.eight.palette.domain.board.entity.Board;
import com.eight.palette.domain.user.entity.User;
import com.eight.palette.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Invite extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invite_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User invitedUser;

    @Builder
    public Invite(Board board, User invitedUser) {
        this.board = board;
        this.invitedUser = invitedUser;
    }


}
