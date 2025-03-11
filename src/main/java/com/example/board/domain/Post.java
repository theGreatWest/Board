package com.example.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

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

//    private Long boardId;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude // cotroller 에서 로그 찍을 때 무한 반복 되는 것을 막아준다.
    private Board board; // @OneToMany 는 객체이기 때문에 Long 이 아닌 객체로 지정해 줘야 한다.

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
//    @Transient
    @OneToMany(
            mappedBy = "post"
    )
    @Where(clause = "status = 'REGISTERED'") // 건수가 너무 많아지면 성능에 문제가 생김
    @OrderBy(value = "id desc") // 정렬 순서 지정
    @Builder.Default
    private List<Reply> replyList = new ArrayList<>();
}
