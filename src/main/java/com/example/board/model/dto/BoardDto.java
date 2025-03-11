package com.example.board.model.dto;

import com.example.board.domain.Post;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardDto {
    private Long id;
    private String boardName;
    private String status;
    @Builder.Default // Builder 호출할 때 누락되지 않도록 반드시 추가해 줄 것
    private List<PostDto> postList = new ArrayList<>();
}
