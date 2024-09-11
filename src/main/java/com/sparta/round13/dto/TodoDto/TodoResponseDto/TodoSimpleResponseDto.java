package com.sparta.round13.dto.TodoDto.TodoResponseDto;


import com.sparta.round13.dto.CommentDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TodoSimpleResponseDto {

    private final Long id;
    private final String todo;
    private final String username;
    private final LocalDateTime modifiedAt;

    private final List<CommentDto> comments;

    public TodoSimpleResponseDto(Long id, String todo, String username, LocalDateTime modifiedAt, List<CommentDto> comments) {
        this.id = id;
        this.todo = todo;
        this.username = username;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
