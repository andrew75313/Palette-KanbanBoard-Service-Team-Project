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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public CommentResponseDto createCommnet(Long cardId, @Valid CommentRequestDto commentRequestDto, User user) {

        User foundUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new BadRequestException("해당 사용자는 존재하지 않습니다.")
        );

        Card foundCard = cardRepository.findById(cardId).orElseThrow(
                () -> new BadRequestException("해당 카드는 존재하지 않습니다.")
        );

        if (!foundCard.getBoard().getUser().getId().equals(foundUser.getId())) {

            List<Long> userIdList = foundCard.getBoard().getInvites().stream()
                    .map(Invite -> Invite.getInvitedUser().getId())
                    .toList();

            if (!userIdList.contains(user.getId())) {
                throw new BadRequestException("보드에 권한이 없습니다.");
            }

        }

        Comment comment = new Comment(commentRequestDto, foundCard, foundUser);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);

    }

}
