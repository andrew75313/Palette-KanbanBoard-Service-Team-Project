package com.eight.palette.domain.card.controller;

import com.eight.palette.domain.card.dto.CardRequestDto;
import com.eight.palette.domain.card.dto.CardResponseDto;
import com.eight.palette.domain.card.service.CardService;
import com.eight.palette.global.dto.DataResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/column/{columnId}/cards")
    public ResponseEntity<DataResponse<CardResponseDto>> createCard(@PathVariable("columnId") Long columnId,
                                                                    @Valid @RequestBody CardRequestDto requestDto) {

        return ResponseEntity.ok(
                new DataResponse<>(200, "카드 생성 성공", cardService.createCard(columnId, requestDto)));
    }

}
