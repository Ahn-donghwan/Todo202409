package com.sparta.round13.repository;

import com.sparta.round13.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    List<Manager> findByTodoId(Long id);

}
