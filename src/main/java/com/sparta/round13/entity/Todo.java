package com.sparta.round13.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Todo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Todo 의 id
    private String todo;
    private String username;
    private Long password;

    public Todo(String todo, String username, Long password) {
        this.todo = todo;
        this.username = username;
        this.password = password;
    }
}
