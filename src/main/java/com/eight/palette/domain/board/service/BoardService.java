package com.eight.palette.domain.board.service;

import com.eight.palette.domain.board.dto.BoardRequestDto;
import com.eight.palette.domain.board.dto.BoardResponseDto;
import com.eight.palette.domain.board.entity.Board;
import com.eight.palette.domain.board.repository.BoardRepository;
import com.eight.palette.domain.user.entity.User;
import com.eight.palette.global.exception.CustomException;
import com.eight.palette.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board getBoard(User user, Long boardId) {

        Board board = boardRepository.findById(boardId).orElseThrow(()
                -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
        );
        if (!board.getUser().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.USER_DIFFERENT);
        }

        return board;

    }

    public BoardResponseDto createBoard(User user, BoardRequestDto requestDto){

        Board board = Board.builder()
                .user(user)
                .title(requestDto.getTitle())
                .intro(requestDto.getIntro())
                .build();

       boardRepository.save(board);

        return new BoardResponseDto(board);

    }

    @Transactional
    public BoardResponseDto updateBoard(User user, Long boardId, BoardRequestDto requestDto) {

        Board board = getBoard(user, boardId);
        board.update(requestDto.getTitle(), requestDto.getIntro());
        return new BoardResponseDto(board);

    }
}
