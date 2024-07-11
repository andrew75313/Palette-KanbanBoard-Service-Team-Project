package com.eight.palette.domain.invite.Repository;

import com.eight.palette.domain.invite.entity.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InviteRepository extends JpaRepository<Invite, Long> {

    List<Invite> findAllByInvitedUserId(Long userId);

}