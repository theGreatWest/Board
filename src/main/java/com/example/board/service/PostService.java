package com.example.board.service;

import com.example.board.common.Api;
import com.example.board.common.Pagination;
import com.example.board.domain.Post;
import com.example.board.model.request.PostRequest;
import com.example.board.model.request.PostViewRequest;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    private final ReplyService replyService;

    public Post create(PostRequest request){
        var optionalBoard = boardRepository.findById(request.getBoardId());

        if(optionalBoard.isEmpty()){
            throw new RuntimeException("일치하는 보드가 없습니다.");
        }

//        var board = boardRepository.findById(request.getBoardId()).get(); // 원칙은 얘가 있는지 없는지 확인해야 함

        Post entity = Post.builder()
//                .boardId(request.getBoardId()) // 임시 고정
//                .board(board) // 객체 전달
                .board(optionalBoard.get())

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
//                    var replies = replyService.findAllByPostId(request.getId());
//                    item.setReplyList(replies);
                    // mappedby 를 통해 자동으로 값을 가져오기 때문에 필요 없다.

                    return item;
                })
                .orElseThrow(
                        ()->{ // 없을 때 default 처리
                            return new RuntimeException("해당 게시글이 존재하지 않습니다 : "+request.getId());
                        }
                );

        return post;
    }

//    public List<Post> all(Pageable pageable){
    public Api<List<Post>> all(Pageable pageable){
        var list = postRepository.findAll(pageable);

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        var response = Api.<List<Post>>builder()
                .body(list.toList())
                .pagination(pagination)
                .build();

//        return postRepository.findAll();
        return response;
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
