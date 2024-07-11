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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ColumnInfoService {

    private final ColumnsRepository columnsRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public ColumnInfoResponseDto createColumn(Long boardId,
                                              @Valid ColumnInfoRequestDto columnInfoResponseDto,
                                              User user) {

        User foundUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new BadRequestException("해당 사용자는 존재하지 않습니다.")
        );

        Board foundBoard = validateBoardOwnership(boardId, foundUser);

        List<String> foundColumnStatuses = columnsRepository.findByBoardId(boardId).stream()
                .map(ColumnInfo::getStatus).toList();

        Set<String> requiredStatuses = new HashSet<>();
        requiredStatuses.add(RequiredStatus.UPCOMING.getColumnStatus());
        requiredStatuses.add(RequiredStatus.IN_PROGRESS.getColumnStatus());
        requiredStatuses.add(RequiredStatus.DONE.getColumnStatus());

        for (String requiredStatus : requiredStatuses) {
            if (!foundColumnStatuses.contains(requiredStatus)) {
                throw new BadRequestException("필수 컬럼이 존재하지 않습니다.");
            }
        }

        if (foundColumnStatuses.contains(columnInfoResponseDto.getStatus())) {
            throw new BadRequestException("중복된 상태 이름입니다.");
        }

        ColumnInfo columnInfo = new ColumnInfo(columnInfoResponseDto, foundBoard);

        columnInfo.updatePosition(foundColumnStatuses.size() + 1);

        columnsRepository.save(columnInfo);

        return new ColumnInfoResponseDto(columnInfo);

    }

    public void deleteColumn(Long boardId, Long columnInfoId, User user) {

        User foundUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new BadRequestException("해당 사용자는 존재하지 않습니다.")
        );

        validateBoardOwnership(boardId, foundUser);

        ColumnInfo foundColumn = columnsRepository.findById(columnInfoId).orElseThrow(
                () -> new BadRequestException("해당 컬럼은 존재하지 않습니다.")
        );

        if (foundColumn.getBoard().getId() != boardId) {
            throw new BadRequestException("해당 컬럼은 보드에 존재하지 않습니다.");
        }

        Set<String> requiredStatuses = new HashSet<>();
        requiredStatuses.add(RequiredStatus.UPCOMING.getColumnStatus());
        requiredStatuses.add(RequiredStatus.IN_PROGRESS.getColumnStatus());
        requiredStatuses.add(RequiredStatus.DONE.getColumnStatus());

        if (requiredStatuses.contains(foundColumn.getStatus())) {
            throw new BadRequestException("필수 컬럼은 삭제할 수 없습니다.");
        }

        columnsRepository.delete(foundColumn);

    }

    @Transactional
    public void moveColumn(Long boardId, Long columnInfoId, Integer newPosition, User user) {

        User foundUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new BadRequestException("해당 사용자는 존재하지 않습니다.")
        );

        validateBoardOwnership(boardId, foundUser);

        ColumnInfo foundColumn = columnsRepository.findById(columnInfoId).orElseThrow(
                () -> new BadRequestException("해당 컬럼은 존재하지 않습니다.")
        );

        if (foundColumn.getBoard().getId() != boardId) {
            throw new BadRequestException("해당 컬럼은 보드에 존재하지 않습니다.");
        }

        if (foundColumn.getPosition() == newPosition) {
            return;
        }

        List<ColumnInfo> columnList = columnsRepository.findByBoardIdOrderByPosition(boardId);

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

    public Board validateBoardOwnership(Long boardId, User user) {

        Board foundBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new BadRequestException("해당 보드는 존재하지 않습니다.")
        );

        if (foundBoard.getUser().getId() != user.getId()) {
            throw new BadRequestException("보드 권한이 없습니다.");
        }

        return foundBoard;

    }

}
