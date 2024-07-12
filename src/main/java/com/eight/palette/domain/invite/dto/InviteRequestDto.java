package com.eight.palette.domain.invite.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InviteRequestDto {

    @NotNull(message = "초대할 사용자 ID는 비워둘 수 없습니다.")
    private Long invitedUserId;

}