package com.eight.palette.domain.card.controller;

import com.eight.palette.domain.card.dto.CardRequestDto;
import com.eight.palette.domain.card.dto.CardResponseDto;
import com.eight.palette.domain.card.service.CardService;
import com.eight.palette.global.dto.DataResponse;
import com.eight.palette.global.dto.MessageResponse;
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

    @PutMapping("/cards/{cardId}")
    public ResponseEntity<DataResponse<CardResponseDto>> updateCard(@PathVariable("cardId") Long cardId,
                                                                    @Valid @RequestBody CardRequestDto requestDto) {
        return ResponseEntity.ok(
                new DataResponse<>(200, "카드 수정 성공", cardService.updateCard(cardId, requestDto)));
    }

    @PutMapping("/cards/{cardId}/move")
    public ResponseEntity<MessageResponse> moveCard(@PathVariable("cardId") Long cardId,
                                                    @RequestParam("position") Integer newPosition) {

        cardService.moveCard(cardId, newPosition);

        return ResponseEntity.ok(
                new MessageResponse(200, "카드 순서 변경 성공"));
    }

    @PutMapping("/column/{columnId}/cards/{cardId}/move")
    public ResponseEntity<MessageResponse> moveColumnCard(@PathVariable("columnId") Long columnId,
                                                          @PathVariable("cardId") Long cardId) {

        cardService.moveColumnCard(columnId, cardId);

        return ResponseEntity.ok(
                new MessageResponse(200, "카드 순서 변경 성공"));
    }
}
