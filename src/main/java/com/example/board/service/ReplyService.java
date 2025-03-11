package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.domain.Reply;
import com.example.board.model.request.ReplyRequest;
import com.example.board.repository.PostRepository;
import com.example.board.repository.ReplyRepository;
import com.example.board.service.converter.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
//    private final PostConverter postConverter;

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public Reply create(ReplyRequest request) {
        var optionalPostEntity = postRepository.findById(request.getPostId());

        if(optionalPostEntity.isEmpty()) throw new RuntimeException("게시물이 존재하지 않습니다 : "+request.getPostId());

        Reply reply = Reply.builder()
//                .postId(request.getPostId())
                .post(optionalPostEntity.get())

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
