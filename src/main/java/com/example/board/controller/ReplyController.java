package com.example.board.controller;

import com.example.board.domain.Reply;
import com.example.board.model.request.ReplyRequest;
import com.example.board.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/create")
    public Reply create(
            @Valid
            @RequestBody ReplyRequest request
    ) {
        return replyService.create(request);
    }
}
