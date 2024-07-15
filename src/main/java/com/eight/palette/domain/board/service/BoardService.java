package com.eight.palette.domain.board.service;

import com.eight.palette.domain.board.dto.BoardRequestDto;
import com.eight.palette.domain.board.dto.BoardResponseDto;
import com.eight.palette.domain.board.entity.Board;
import com.eight.palette.domain.board.repository.BoardRepository;
import com.eight.palette.domain.column.service.ColumnInfoService;
import com.eight.palette.domain.invite.Repository.InviteRepository;
import com.eight.palette.domain.invite.entity.Invite;
import com.eight.palette.domain.user.entity.User;
import com.eight.palette.domain.user.entity.UserRoleEnum;
import com.eight.palette.domain.user.repository.UserRepository;
import com.eight.palette.global.exception.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final InviteRepository inviteRepository;
    private final ColumnInfoService columnInfoService;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository, InviteRepository inviteRepository, ColumnInfoService columnInfoService) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.inviteRepository = inviteRepository;
        this.columnInfoService = columnInfoService;
    }

    public BoardResponseDto createBoard(User user, BoardRequestDto requestDto) {

        if (user.getRole() != UserRoleEnum.MANAGER) {
            throw new BadRequestException("보드를 생성할 권한이 없습니다.");
        }

        Board board = Board.builder()
                .user(user)
                .title(requestDto.getTitle())
                .intro(requestDto.getIntro())
                .status(Board.Status.ACTIVE)
                .build();

        boardRepository.save(board);

        columnInfoService.setupInitialColumns(board);

        return new BoardResponseDto(board);

    }

    @Transactional
    public BoardResponseDto updateBoard(User user, Long boardId, BoardRequestDto requestDto) {

        if (user.getRole() != UserRoleEnum.MANAGER) {
            throw new BadRequestException("보드를 수정할 권한이 없습니다.");
        }

        Board board = getBoards(user, boardId);
        board.update(requestDto.getTitle(), requestDto.getIntro());
        return new BoardResponseDto(board);

    }

    public void deleteBoard(User user, Long boardId) {

        if (user.getRole() != UserRoleEnum.MANAGER) {
            throw new BadRequestException("보드를 삭제할 권한이 없습니다.");
        }

        Board board = getBoards(user, boardId);

        if (board.getStatus() == Board.Status.DELETED) {
            throw new BadRequestException("이미 삭제된 보드입니다.");
        }

        board.delete();
        boardRepository.save(board);

    }

    public Page<BoardResponseDto> getBoard(User user, int page, int size) {

        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Board> boardPage;
        if (user.getRole() == UserRoleEnum.MANAGER) {
            boardPage = boardRepository.findAllByStatusOrderByCreatedAtDesc(Board.Status.ACTIVE, pageable);
        }  else {
            List<Long> invitedBoardIds = inviteRepository.findAllByInvitedUserId(user.getId()).stream()
                    .map(invite -> invite.getBoard().getId())
                    .toList();

            boardPage = boardRepository.findByStatusAndIdIn(Board.Status.ACTIVE, invitedBoardIds, pageable);
        }

        return boardPage.map(BoardResponseDto::new);

    }

    @Transactional
    public void inviteBoard(User inviter, Long boardId, Long invitedUserId) {

        if (inviter.getRole() != UserRoleEnum.MANAGER) {
            throw new BadRequestException("보드에 초대 권한이 없습니다.");
        }

        Board board = getBoards(inviter, boardId);

        User invitedUser = userRepository.findById(invitedUserId)
                .orElseThrow(() -> new BadRequestException("초대할 사용자를 찾을 수 없습니다."));

        if (inviter.getId().equals(invitedUser.getId())) {
            throw new BadRequestException("본인을 초대할 수 없습니다.");
        }

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

    public BoardResponseDto getBoardInfo(Long id) {
       Board board = boardRepository.findById(id).orElse(null);

       if(board == null) {
           throw new BadRequestException("해당 보드는 존재하지 않습니다");
       }

        return new BoardResponseDto(board);
    }

    public Board getBoards(User user, Long boardId) {

        Board board = boardRepository.findById(boardId).orElseThrow(()
                -> new BadRequestException("보드를 찾을 수 없습니다.")
        );

        if (board.getStatus() == Board.Status.DELETED) {
            throw new BadRequestException("이미 삭제된 보드입니다.");
        }

//        if (!board.getUser().getId().equals(user.getId())) {
//            throw new BadRequestException("다른 사용자의 보드입니다.");
//        }

        return board;

    }
}
