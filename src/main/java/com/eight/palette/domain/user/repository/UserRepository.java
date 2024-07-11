package com.eight.palette.domain.user.repository;

import com.eight.palette.domain.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "update user " +
                    "set refresh_token = :refreshToken " +
                    "where id = :id")
    void setTokenValue(@Param("id") Long id, @Param("refreshToken") String refreshToken);

}
