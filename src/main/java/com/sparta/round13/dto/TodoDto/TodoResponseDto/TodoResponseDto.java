package com.sparta.round13.dto.TodoDto.TodoResponseDto;

import com.sparta.round13.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TodoResponseDto {

    private final Long id;
    private final String todo;
    private final String username;
    private final Long password;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public TodoResponseDto(Long id, String todo, String username, Long password,
                           LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.todo = todo;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;

    }

}
