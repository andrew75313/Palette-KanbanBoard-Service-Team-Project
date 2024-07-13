package com.eight.palette.domain.comment.service;

import com.eight.palette.domain.card.entity.Card;
import com.eight.palette.domain.card.repository.CardRepository;
import com.eight.palette.domain.comment.dto.CommentRequestDto;
import com.eight.palette.domain.comment.dto.CommentResponseDto;
import com.eight.palette.domain.comment.entity.Comment;
import com.eight.palette.domain.comment.repository.CommentRepository;
import com.eight.palette.domain.user.entity.User;
import com.eight.palette.domain.user.repository.UserRepository;
import com.eight.palette.global.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, CardRepository cardRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public CommentResponseDto createCommnet(Long cardId, CommentRequestDto commentRequestDto, User user) {

        User foundUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new BadRequestException("해당 사용자는 존재하지 않습니다.")
        );

        Card foundCard = validateCard(cardId);

        validateBoardOwnership(foundCard, foundUser);

        Comment comment = Comment.builder()
                .comment(commentRequestDto.getComment())
                .card(foundCard)
                .user(foundUser)
                .build();

        commentRepository.save(comment);

        return new CommentResponseDto(comment);

    }

    public List<CommentResponseDto> getComments(Long cardId, User user) {

        User foundUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new BadRequestException("해당 사용자는 존재하지 않습니다.")
        );

        Card foundCard = validateCard(cardId);

        validateBoardOwnership(foundCard, foundUser);

        List<CommentResponseDto> responseDtoList = commentRepository.findByCardIdOrderByCreatedAtDesc(cardId).stream()
                .map(CommentResponseDto::new)
                .toList();

        return responseDtoList;

    }

    public Card validateCard(Long cardId) {

        Card foundCard = cardRepository.findById(cardId).orElseThrow(
                () -> new BadRequestException("해당 카드는 존재하지 않습니다.")
        );

        if (foundCard.getStatus() == Card.Status.DELETED) {
            throw new BadRequestException("이미 삭제된 카드입니다.");
        }

        return foundCard;
    }

    public void validateBoardOwnership(Card card, User user) {

        if (!card.getColumnInfo().getBoard().getUser().getId().equals(user.getId())) {

            List<Long> userIdList = card.getColumnInfo().getBoard().getInvites().stream()
                    .map(Invite -> Invite.getInvitedUser().getId())
                    .toList();

            if (!userIdList.contains(user.getId())) {
                throw new BadRequestException("보드에 권한이 없습니다.");
            }

        }

    }

}
