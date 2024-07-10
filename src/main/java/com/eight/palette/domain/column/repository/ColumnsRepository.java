package com.eight.palette.domain.column.repository;

import com.eight.palette.domain.column.entity.ColumnInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnsRepository extends JpaRepository<ColumnInfo, Integer> {
}
