package com.sparta.round13.dto.TodoDto.TodoResponseDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoSimpleResponseDto {

    private final Long id;
    private final String todo;
    private final String username;
    private final LocalDateTime modifiedAt;

    public TodoSimpleResponseDto(Long id, String todo, String username, LocalDateTime modifiedAt) {
        this.id = id;
        this.todo = todo;
        this.username = username;
        this.modifiedAt = modifiedAt;
    }
}
