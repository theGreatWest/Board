package com.example.board.repository;

import com.example.board.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // select * from reply where post_id = ? and status = ? order by id desc;
    List<Reply> findByPostIdAndStatusOrderByIdDesc(Long postId, String status);

}
