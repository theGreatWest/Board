package com.example.board.repository;

import com.example.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    // select * from user where score >= 사용자입력값
    // https://docs.spring.vmware.com/spring-data-jpa-distribution/docs/3.1.13/reference/html/index.html#jpa.query-methods
    //    public List<Post> findAllScoreGreaterThanEqual(int score);

    // select * from post
    // where  id = ? and  status = ?
    // order by id desc limit 1;
    public Optional<Post> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);
}
