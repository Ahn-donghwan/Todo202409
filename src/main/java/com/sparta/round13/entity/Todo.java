package com.sparta.round13.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Long password;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private final List<Comment> comments = new ArrayList<>();


    public Todo(String todo, User user, Long password) {
        this.todo = todo;
        this.user = user;
        this.password = password;
    }

    public void updateTodo(String todo, User user) {
        this.todo = todo;
        this.user = user;
    }
}
