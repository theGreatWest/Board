package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.model.PostRequest;
import com.example.board.model.PostViewRequest;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ReplyService replyService;

    public Post create(PostRequest request){
        Post entity = Post.builder()
                .boardId(1L) // 임시 고정
                .userName(request.getUserName())
                .password(request.getPassword())
                .email(request.getEmail())
                .status("REGISTERED")
                .title(request.getTitle())
                .content(request.getContent())
                .postedAt(LocalDateTime.now())
                .build();

        return postRepository.save(entity);
    }

    public Post view(PostViewRequest request){
        // 게시글이 있는가?
        // 비밀번호가 맞는가?
        var post
//                = postRepository.findById(request.getId())
                = postRepository.findFirstByIdAndStatusOrderByIdDesc(request.getId(), "REGISTERED")
                .map(item -> {
                    // entity 존재 X
                    if(!item.getPassword().equals(request.getPassword())){
                        var format = "패스워드가 맞지 않습니다 %s vs %s";
                        throw new RuntimeException(String.format(format, item.getPassword(), request.getPassword()));
                    }
                    // entity 존재 O
                    // 답변 글도 같이 내려오게
                    var replies = replyService.findAllByPostId(request.getId());
                    item.setReplies(replies);

                    return item;
                })
                .orElseThrow(
                        ()->{ // 없을 때 default 처리
                            return new RuntimeException("해당 게시글이 존재하지 않습니다 : "+request.getId());
                        }
                );

        return post;
    }

    public List<Post> all(){
        return postRepository.findAll();
    }

    public void delete(PostViewRequest request){
        var post = postRepository.findById(request.getId())
                        .map(item ->{
                            if(!request.getPassword().equals(item.getPassword())){
                                throw new RuntimeException("패스워드가 일치하지 않습니다.");
                            }
                            return item;
                        }).orElseThrow(
                        ()->{ // 없을 때 default 처리
                            return new RuntimeException("해당 게시글이 존재하지 않습니다 : "+request.getId());
                        });
        postRepository.delete(post);
    }
}
