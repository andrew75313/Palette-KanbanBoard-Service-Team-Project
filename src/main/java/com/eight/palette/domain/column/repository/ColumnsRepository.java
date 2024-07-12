package com.eight.palette.domain.column.repository;

import com.eight.palette.domain.column.entity.ColumnInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnsRepository extends JpaRepository<ColumnInfo, Long> {

    @Query("SELECT c FROM ColumnInfo c WHERE c.board.id = :boardId AND c.status = 'ACTIVE' ORDER BY c.position ASC")
    List<ColumnInfo> findActiveColumnsByBoardIdOrderByPosition(Long boardId);
}
