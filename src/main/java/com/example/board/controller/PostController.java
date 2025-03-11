package com.example.board.controller;

import com.example.board.domain.Post;
import com.example.board.model.PostRequest;
import com.example.board.model.PostViewRequest;
import com.example.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/all")
    public List<Post> list() {
        return postService.all();
    }

    @PostMapping("/delete")
    public void delete(
            @Valid
            @RequestBody PostViewRequest request
    ) {
        postService.delete(request);
    }
}
