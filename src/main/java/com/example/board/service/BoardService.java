package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.model.dto.BoardDto;
import com.example.board.model.request.BoardRequest;
import com.example.board.repository.BoardRepository;
import com.example.board.service.converter.BoardConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardConverter boardconverter;

    public BoardDto create(BoardRequest boardRequest) {
        Board entity = Board.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        log.info("{}",entity);

        var saveEntity = boardRepository.save(entity);

        return boardconverter.toDto(saveEntity);
    }
//
//    public Board create(BoardRequest boardRequest) {
//        Board entity = Board.builder()
//                .boardName(boardRequest.getBoardName())
//                .status("REGISTERED")
//                .build();
//
//        return boardRepository.save(entity);
//    }


    public BoardDto view(Long id) {
        var entity = boardRepository.findById(id).get(); // 있는거라고 100% 가정하고 짠 코드

        return boardconverter.toDto(entity);
    }
}
