package com.eight.palette.domain.board.entity;

import com.eight.palette.domain.invite.entity.Invite;
import com.eight.palette.domain.user.entity.User;
import com.eight.palette.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String intro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public void delete() {
        this.status = Status.DELETED;
    }

    public boolean isActive() {
        return this.status == Status.ACTIVE;
    }

    public void update(String title, String intro) {
        this.title = title;
        this.intro = intro;
    }

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invite> invites = new ArrayList<>();

}
