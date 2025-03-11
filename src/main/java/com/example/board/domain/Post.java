package com.example.board.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long boardId;
    private String userName;
    private String password;
    private String email;
    private String status;
    private String title;
    // 속성이 안 맞는다고 뜨면
    @Column(columnDefinition = "TEXT") // 이렇게 지정해 주면 된다.
    private String content;
    private LocalDateTime postedAt;

    // 이 변수는 DB 의 컬럼이 아니라고 꼭 명시해 줘야 한다.
    @Transient
    private List<Reply> replies = List.of();
}
