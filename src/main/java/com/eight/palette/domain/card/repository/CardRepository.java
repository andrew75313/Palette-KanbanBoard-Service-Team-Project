package com.eight.palette.domain.card.repository;

import com.eight.palette.domain.card.entity.Card;
import com.eight.palette.domain.column.entity.ColumnInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByColumnInfo(ColumnInfo columnInfo);
}
