package com.eight.palette.domain.column.repository;

import com.eight.palette.domain.column.entity.ColumnInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColumnsRepository extends JpaRepository<ColumnInfo, Long> {

    List<ColumnInfo> findByBoardId(Long boardId);
    List<ColumnInfo> findByBoardIdOrderByPosition(Long boardId);
}
