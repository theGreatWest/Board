package com.example.board.service.converter;

import com.example.board.domain.Board;
import com.example.board.model.dto.BoardDto;
import com.example.board.model.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

// 데이터 변환하는 역할

@Service
@RequiredArgsConstructor
public class BoardConverter {
    private final PostConverter postConverter;

    public BoardDto toDto(Board boardEntity){
        var postList = boardEntity.getPostList().stream()
                        .map(postEntity -> {
                            return postConverter.toDto(postEntity);
                        }).toList();
//  위의 코드와 동일한 코드 : .map(postConverter::toDto).toList();

        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .postList(postList) // mappedby 를 해놔서 연결된 N 개의 데이터를 가져옴
                .build();
    }
}
