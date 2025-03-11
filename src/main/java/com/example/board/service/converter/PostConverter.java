package com.example.board.service.converter;

import com.example.board.domain.Post;
import com.example.board.model.dto.PostDto;
import org.springframework.stereotype.Service;

@Service
public class PostConverter {

    public PostDto toDto(Post postEntity){
        return PostDto.builder()
                .id(postEntity.getId())
                .boardId(postEntity.getId())
                .userName(postEntity.getUserName())
                .status(postEntity.getStatus())
                .email(postEntity.getEmail())
                .password(postEntity.getPassword())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(postEntity.getPostedAt())
                .build();
    }
}
