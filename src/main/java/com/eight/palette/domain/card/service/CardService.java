package com.eight.palette.domain.card.service;

import com.eight.palette.domain.card.dto.CardRequestDto;
import com.eight.palette.domain.card.dto.CardResponseDto;
import com.eight.palette.domain.card.entity.Card;
import com.eight.palette.domain.card.repository.CardRepository;
import com.eight.palette.domain.column.entity.ColumnInfo;
import com.eight.palette.domain.column.repository.ColumnsRepository;
import com.eight.palette.global.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final ColumnsRepository columnsRepository;


    public CardService(CardRepository cardRepository, ColumnsRepository columnsRepository) {

        this.cardRepository = cardRepository;
        this.columnsRepository = columnsRepository;

    }

    public CardResponseDto createCard(Long columnId, CardRequestDto requestDto) {

        ColumnInfo columnInfo = columnsRepository.findById(columnId).orElseThrow(()
                -> new NotFoundException("해당 컬럼을 찾지 못했습니다.")
        );

        int cardSize = columnInfo.getCardList().size();
        int position = 1;

        if(cardSize != 0) {
            position = cardSize + 1;
        }

        Card card = Card.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .deadLineDate(requestDto.getDeadLineDate())
                .worker(requestDto.getWorker())
                .columnInfo(columnInfo)
                .status(Card.Status.ACTIVE)
                .position(position)
                .build();

        cardRepository.save(card);

        return new CardResponseDto(card);

    }

    @Transactional
    public CardResponseDto updateCard(Long cardId, CardRequestDto requestDto) {

        Card foundCard = cardRepository.findById(cardId).orElseThrow(()
                -> new NotFoundException("해당 카드를 찾지 못했습니다.")
        );

        foundCard.updateTitle(requestDto.getTitle());

        cardRepository.save(foundCard);

        return new CardResponseDto(foundCard);

    }
}
