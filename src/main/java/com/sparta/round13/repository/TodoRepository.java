package com.sparta.round13.repository;

import com.sparta.round13.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByOrderByModifiedAtDesc();
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);
}
