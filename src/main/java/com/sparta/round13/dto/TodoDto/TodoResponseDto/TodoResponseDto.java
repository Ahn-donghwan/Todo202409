package com.sparta.round13.dto.TodoDto.TodoResponseDto;

import com.sparta.round13.entity.Comment;
import com.sparta.round13.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TodoResponseDto {

    private final Long todoId;
    private final String todo;
    private final Long userId;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public TodoResponseDto(Long todoId, String todo, Long userId, String username,
                           LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.todoId = todoId;
        this.todo = todo;
        this.userId = userId;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;

    }

}
