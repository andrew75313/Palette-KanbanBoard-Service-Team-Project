package com.eight.palette.domain.column.service;

import com.eight.palette.domain.board.entity.Board;
import com.eight.palette.domain.board.repository.BoardRepository;
import com.eight.palette.domain.column.dto.ColumnInfoRequestDto;
import com.eight.palette.domain.column.dto.ColumnInfoResponseDto;
import com.eight.palette.domain.column.entity.ColumnInfo;
import com.eight.palette.domain.column.entity.RequiredStatus;
import com.eight.palette.domain.column.repository.ColumnsRepository;
import com.eight.palette.domain.user.entity.User;
import com.eight.palette.domain.user.repository.UserRepository;
import com.eight.palette.global.exception.BadRequestException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ColumnInfoService {

    private final ColumnsRepository columnsRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public ColumnInfoService(ColumnsRepository columnsRepository, UserRepository userRepository, BoardRepository boardRepository) {
        this.columnsRepository = columnsRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    public ColumnInfoResponseDto createColumn(Long boardId,
                                              ColumnInfoRequestDto columnInfoResponseDto,
                                              User user) {

        User foundUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new BadRequestException("해당 사용자는 존재하지 않습니다.")
        );

        Board foundBoard = validateBoardOwnership(boardId, foundUser);

        List<String> foundColumnStatuses = columnsRepository.findActiveColumnsByBoardIdOrderByPosition(boardId).stream()
                .map(ColumnInfo::getStatusName).toList();

        Set<String> requiredStatuses = Stream.of(RequiredStatus.values())
                .map(RequiredStatus::getColumnStatus)
                .collect(Collectors.toSet());

        for (String requiredStatus : requiredStatuses) {
            if (!foundColumnStatuses.contains(requiredStatus)) {
                throw new BadRequestException("필수 컬럼이 존재하지 않습니다.");
            }
        }

        if (foundColumnStatuses.contains(columnInfoResponseDto.getStatusName())) {
            throw new BadRequestException("중복된 상태 이름입니다.");
        }

        ColumnInfo columnInfo = ColumnInfo.builder()
                .statusName(columnInfoResponseDto.getStatusName())
                .position(foundColumnStatuses.size() + 1)
                .status(ColumnInfo.Status.ACTIVE)
                .board(foundBoard)
                .build();

        columnsRepository.save(columnInfo);

        return new ColumnInfoResponseDto(columnInfo);

    }

    @Transactional
    public void deleteColumn(Long boardId, Long columnInfoId, User user) {

        User foundUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new BadRequestException("해당 사용자는 존재하지 않습니다.")
        );

        validateBoardOwnership(boardId, foundUser);

        ColumnInfo foundColumn = validateColumnInfo(columnInfoId);

        if (foundColumn.getBoard().getId() != boardId) {
            throw new BadRequestException("해당 컬럼은 보드에 존재하지 않습니다.");
        }

        Set<String> requiredStatuses = Stream.of(RequiredStatus.values())
                .map(RequiredStatus::getColumnStatus)
                .collect(Collectors.toSet());

        if (requiredStatuses.contains(foundColumn.getStatusName())) {
            throw new BadRequestException("필수 컬럼은 삭제할 수 없습니다.");
        }

        foundColumn.delete();

        columnsRepository.save(foundColumn);

    }

    @Transactional
    public void moveColumn(Long boardId, Long columnInfoId, Integer newPosition, User user) {

        User foundUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new BadRequestException("해당 사용자는 존재하지 않습니다.")
        );

        validateBoardOwnership(boardId, foundUser);

        ColumnInfo foundColumn = validateColumnInfo(columnInfoId);

        if (foundColumn.getBoard().getId() != boardId) {
            throw new BadRequestException("해당 컬럼은 보드에 존재하지 않습니다.");
        }

        if (foundColumn.getPosition() == newPosition) {
            return;
        }

        List<ColumnInfo> columnList = columnsRepository.findActiveColumnsByBoardIdOrderByPosition(boardId);

        newPosition = newPosition > columnList.size() ? columnList.size() : newPosition;

        foundColumn.updatePosition(newPosition);

        Integer position = 1;
        for (ColumnInfo column : columnList) {
            if (!column.getId().equals(foundColumn.getId())) {
                if (position == newPosition) {
                    position++;
                }

                column.updatePosition(position);

                position++;
            }
        }

    }

    public List<ColumnInfo> getColumns(Long boardId) {
        return columnsRepository.findActiveColumnsByBoardIdOrderByPosition(boardId);
    }


    @Transactional
    public void setupInitialColumns(Board board) {

        ColumnInfo upcomingColumn = ColumnInfo.builder()
                .statusName(RequiredStatus.UPCOMING.getColumnStatus())
                .position(1)
                .status(ColumnInfo.Status.ACTIVE)
                .board(board)
                .build();

        columnsRepository.save(upcomingColumn);

        ColumnInfo inProgressColumn = ColumnInfo.builder()
                .statusName(RequiredStatus.IN_PROGRESS.getColumnStatus())
                .position(2)
                .status(ColumnInfo.Status.ACTIVE)
                .board(board)
                .build();

        columnsRepository.save(inProgressColumn);

        ColumnInfo doneColumn = ColumnInfo.builder()
                .statusName(RequiredStatus.DONE.getColumnStatus())
                .position(3)
                .status(ColumnInfo.Status.ACTIVE)
                .board(board)
                .build();

        columnsRepository.save(doneColumn);

    }

    public ColumnInfo validateColumnInfo(Long columnInfoId) {

        ColumnInfo foundColumn = columnsRepository.findById(columnInfoId).orElseThrow(
                () -> new BadRequestException("해당 컬럼은 존재하지 않습니다.")
        );

        if (!foundColumn.isActive()) {
            throw new BadRequestException("이미 삭제된 컬럼입니다.");
        }

        return foundColumn;

    }

    public Board validateBoardOwnership(Long boardId, User user) {

        Board foundBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new BadRequestException("해당 보드는 존재하지 않습니다.")
        );

//        if (foundBoard.getUser().getId() != user.getId()) {
//            throw new BadRequestException("보드 권한이 없습니다.");
//        }

        return foundBoard;

    }

}
