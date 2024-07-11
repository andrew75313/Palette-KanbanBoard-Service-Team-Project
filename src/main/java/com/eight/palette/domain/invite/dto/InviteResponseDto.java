package com.eight.palette.domain.invite.dto;

import com.eight.palette.domain.invite.entity.Invite;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InviteResponseDto {

    private Long inviteId;
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    public InviteResponseDto(Invite invite)
    {
        this.inviteId = invite.getId();
        this.username = invite.getInvitedUser().getUsername();
        this.createdAt = invite.getCreatedAt();
        this.modifiedAt = invite.getModifiedAt();
    }
}