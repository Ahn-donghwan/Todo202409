package com.sparta.round13.entity;

import com.sparta.round13.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private final List<Comment> comments = new ArrayList<>();

    public Todo(String todo, String username, Long password) {
        this.todo = todo;
        this.username = username;
        this.password = password;
    }

    public void updateTodo(String todo, String username) {
        this.todo = todo;
        this.username = username;
    }
}
