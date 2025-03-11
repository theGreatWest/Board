package com.example.board.controller;

import com.example.board.common.Api;
import com.example.board.domain.Post;
import com.example.board.model.request.PostRequest;
import com.example.board.model.request.PostViewRequest;
import com.example.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public Post create(
            @Valid
            @RequestBody
            PostRequest postRequest
    ) {
        return postService.create(postRequest);
    }

    @PostMapping("/view")
    public Post view(
            @Valid
            @RequestBody PostViewRequest request
    ) {
        return postService.view(request);
    }

    // http://localhost:8080/api/post/all?page=0&size=5
    @GetMapping("/all")
    public Api<List<Post>> list(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) // id 를 기준으로 내림차순으로 보고 싶을 때
            Pageable pageable
    ) {
        return postService.all(pageable);
    }

    @PostMapping("/delete")
    public void delete(
            @Valid
            @RequestBody PostViewRequest request
    ) {
        postService.delete(request);
    }
}
