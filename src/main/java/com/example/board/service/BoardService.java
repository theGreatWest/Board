package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.domain.Post;
import com.example.board.model.BoardRequest;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board create(BoardRequest boardRequest){
        Board entity = Board.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        return boardRepository.save(entity);
    }


}
