package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.model.dto.BoardDto;
import com.example.board.model.request.BoardRequest;
import com.example.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
//    public Board createBoard( // BoardDto 로 변경
    public BoardDto createBoard(
            @Valid
            @RequestBody
            BoardRequest boardRequest
    ) {
        return boardService.create(boardRequest);
    }

    @GetMapping("/id/{id}")
    public BoardDto view(
            @PathVariable Long id
    ) {
        var entity = boardService.view(id);
        log.info("result : {}", entity); // 무한반복 -> Post Entity 에서 처리해 줄 것

        return boardService.view(id);
    }
}
