package com.sparta.round13.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo; // 하나의 필드일 뿐

    public Comment(String username, String contents, Todo todo) {
        this.username = username;
        this.contents = contents;
        this.todo = todo;
    }

    public void updateComment(String username, String contents) {
        this.username = username;
        this.contents = contents;
    }
}
