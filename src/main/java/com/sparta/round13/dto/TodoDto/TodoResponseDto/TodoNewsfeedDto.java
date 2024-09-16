package com.sparta.round13.dto.TodoDto.TodoResponseDto;

import com.sparta.round13.entity.Todo;
import com.sparta.round13.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class TodoNewsfeedDto {

    
    private final String todo;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final int count;

    public TodoNewsfeedDto(String todo, String username,
                           LocalDateTime createdAt, LocalDateTime modifiedAt, int count) {
        this.todo = todo;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.count = count;
    }
}
