package com.example.board.model.dto;

import com.example.board.domain.Board;
import com.example.board.domain.Reply;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDto {
    private Long id;
    private Long boardId; // @OneToMany 는 객체이기 때문에 Long 이 아닌 객체로 지정해 줘야 한다.
    private String userName;
    private String password;
    private String email;
    private String status;
    private String title;
    private String content;
    private LocalDateTime postedAt;
    @Builder.Default
    private List<Reply> replyList = new ArrayList<>();
}
