package com.example.board.service;

import com.example.board.domain.Reply;
import com.example.board.model.ReplyRequest;
import com.example.board.repository.ReplyRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    public Reply create(ReplyRequest request) {
        Reply reply = Reply.builder()
                .postId(request.getPostId())
                .userName(request.getUserName())
                .password(request.getPassword())
                .status("REGISTERED")
                .title(request.getTitle())
                .content(request.getContent())
                .repliedAt(LocalDateTime.now())
                .build();

        return replyRepository.save(reply);
    }

    public List<Reply> findAllByPostId(Long id){
        return replyRepository.findByPostIdAndStatusOrderByIdDesc(id, "REGISTERED");
    }
}
