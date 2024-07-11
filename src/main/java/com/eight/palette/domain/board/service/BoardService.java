package com.eight.palette.domain.board.service;

import com.eight.palette.domain.board.dto.BoardRequestDto;
import com.eight.palette.domain.board.dto.BoardResponseDto;
import com.eight.palette.domain.board.entity.Board;
import com.eight.palette.domain.board.repository.BoardRepository;
import com.eight.palette.domain.invite.Repository.InviteRepository;
import com.eight.palette.domain.invite.entity.Invite;
import com.eight.palette.domain.user.entity.User;
import com.eight.palette.domain.user.repository.UserRepository;
import com.eight.palette.global.exception.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final InviteRepository inviteRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository, InviteRepository inviteRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.inviteRepository = inviteRepository;
    }

    public BoardResponseDto createBoard(User user, BoardRequestDto requestDto) {

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

        Board board = getBoards(user, boardId);
        board.update(requestDto.getTitle(), requestDto.getIntro());
        return new BoardResponseDto(board);

    }

    public void deleteBoard(User user, Long boardId) {

        Board board = getBoards(user, boardId);
        boardRepository.delete(board);

    }

    public Page<BoardResponseDto> getBoard(int page, int size) {

        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Board> boardPage = boardRepository.findAllByOrderByCreatedAtDesc(pageable);
        return boardPage.map(BoardResponseDto::new);

    }

    @Transactional
    public void inviteBoard(User inviter, Long boardId, Long invitedUserId) {
        Board board = getBoards(inviter, boardId);

        User invitedUser = userRepository.findById(invitedUserId)
                .orElseThrow(() -> new BadRequestException("초대할 사용자를 찾을 수 없습니다."));

        for (Invite invite : board.getInvites()) {
            if (invite.getInvitedUser().equals(invitedUser)) {
                throw new BadRequestException("이미 초대된 사용자입니다.");
            }
        }

        Invite invite = Invite.builder()
                .board(board)
                .invitedUser(invitedUser)
                .build();

        inviteRepository.save(invite);

    }

    public Board getBoards(User user, Long boardId) {

        Board board = boardRepository.findById(boardId).orElseThrow(()
                -> new BadRequestException("보드를 찾을 수 없습니다.")
        );
        if (!board.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("다른 사용자의 보드입니다.");
        }

        return board;

    }
}
