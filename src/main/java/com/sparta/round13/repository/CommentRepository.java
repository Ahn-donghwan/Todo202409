package com.sparta.round13.repository;

import com.sparta.round13.dto.CommentDto;
import com.sparta.round13.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByTodoId(Long todoId);

    List<CommentDto> findByTodoId(Long todoId);
}
