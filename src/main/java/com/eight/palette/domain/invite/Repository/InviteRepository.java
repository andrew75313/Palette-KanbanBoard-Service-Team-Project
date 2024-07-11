package com.eight.palette.domain.invite.Repository;

import com.eight.palette.domain.invite.entity.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteRepository extends JpaRepository<Invite, Long> {


}