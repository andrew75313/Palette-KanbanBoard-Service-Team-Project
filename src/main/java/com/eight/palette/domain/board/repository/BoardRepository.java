package com.eight.palette.domain.board.repository;

import com.eight.palette.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findById(Long id);

    Page<Board> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Board> findAllByStatusOrderByCreatedAtDesc(Board.Status status, Pageable pageable);
}
