package com.example.board.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "board") // 테이블명
//@Getter
//@Setter
//@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;
    private String status;

    // 1:N 관계이기 때문에 설정해 줘야 된다.
//    @Transient // DB에 포함되어 있지 않기 때문에 이렇게 지정해도 되고
    @OneToMany(
            mappedBy = "board"
    )
    @Where(clause = "status = 'REGISTERED'")
    @OrderBy(value = "id desc") // 정렬 순서 지정
    @Builder.Default // Builder 패턴에서 누락되지 않도록 설정하는 것
    private List<Post> postList = new ArrayList<>();
}
