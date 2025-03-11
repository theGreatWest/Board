package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.model.BoardRequest;
import com.example.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public Board createBoard(
            @Valid
            @RequestBody
            BoardRequest boardRequest
    ) {
        return boardService.create(boardRequest);
    }
}
