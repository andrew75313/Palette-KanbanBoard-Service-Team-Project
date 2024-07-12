package com.eight.palette.domain.card.controller;

import com.eight.palette.domain.card.dto.CardRequestDto;
import com.eight.palette.domain.card.dto.CardResponseDto;
import com.eight.palette.domain.card.service.CardService;
import com.eight.palette.global.dto.DataResponse;
import com.eight.palette.global.dto.MessageResponse;
import jakarta.validation.Valid;
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
                new DataResponse<>(201, "카드 생성 성공 \uD83C\uDCCF", cardService.createCard(columnId, requestDto)));
    }

    @PutMapping("/cards/{cardId}")
    public ResponseEntity<DataResponse<CardResponseDto>> updateCard(@PathVariable("cardId") Long cardId,
                                                                    @Valid @RequestBody CardRequestDto requestDto) {
        return ResponseEntity.ok(
                new DataResponse<>(200, "카드 수정 성공 \uD83C\uDCCF", cardService.updateCard(cardId, requestDto)));
    }

    @PutMapping("/cards/{cardId}/move")
    public ResponseEntity<MessageResponse> moveCard(@PathVariable("cardId") Long cardId,
                                                    @RequestParam("position") Integer newPosition) {

        cardService.moveCard(cardId, newPosition);

        return ResponseEntity.ok(
                new MessageResponse(200, "카드 순서 변경 성공 \uD83C\uDCCF"));
    }

    @PutMapping("/column/{columnId}/cards/{cardId}/move")
    public ResponseEntity<MessageResponse> moveColumnCard(@PathVariable("columnId") Long columnId,
                                                          @PathVariable("cardId") Long cardId) {

        cardService.moveColumnCard(columnId, cardId);

        return ResponseEntity.ok(
                new MessageResponse(200, "카드 순서 변경 성공 \uD83C\uDCCF"));
    }

    @DeleteMapping("/cards/{cardId}")
    public ResponseEntity<MessageResponse> deleteCard(@PathVariable("cardId") Long cardId) {

        cardService.deleteCard(cardId);

        return ResponseEntity.ok(
                new MessageResponse(200, "카드 삭제 성공 \uD83C\uDCCF"));
    }

    @GetMapping("/cards")
    public ResponseEntity<DataResponse<List<CardResponseDto>>> getAllCards() {
        return ResponseEntity.ok(
                new DataResponse<>(200, "카드 조회 성공 \uD83C\uDCCF", cardService.getAllCards()));
    }

    @GetMapping("/cards/worker")
    public ResponseEntity<DataResponse<List<CardResponseDto>>> getCardsByWorker(@RequestParam("worker") String worker) {
        return ResponseEntity.ok(
                new DataResponse<>(200, "카드 조회 성공 \uD83C\uDCCF", cardService.getCardsByWorker(worker)));
    }

    @GetMapping("/cards/column")
    public ResponseEntity<DataResponse<List<CardResponseDto>>> getCardsByColumn(@RequestParam("column") Long columnId) {
        return ResponseEntity.ok(
                new DataResponse<>(200, "카드 조회 성공 \uD83C\uDCCF", cardService.getCardsByColumn(columnId)));
    }
}
