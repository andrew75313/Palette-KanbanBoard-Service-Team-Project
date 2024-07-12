package com.eight.palette.domain.invite.dto;

import com.eight.palette.domain.invite.entity.Invite;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InviteResponseDto {

    private final Long inviteId;
    private final String username;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;

    public InviteResponseDto(Invite invite)
    {
        this.inviteId = invite.getId();
        this.username = invite.getInvitedUser().getUsername();
        this.createdAt = invite.getCreatedAt();
        this.modifiedAt = invite.getModifiedAt();
    }
}