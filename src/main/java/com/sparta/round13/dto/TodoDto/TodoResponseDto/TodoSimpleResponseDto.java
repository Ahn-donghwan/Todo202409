package com.sparta.round13.dto.TodoDto.TodoResponseDto;


import com.sparta.round13.dto.commentDto.CommentDto;
import com.sparta.round13.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TodoSimpleResponseDto {

    private final Long id;
    private final String todo;
    private final User user;
    private final LocalDateTime modifiedAt;

    private final List<CommentDto> comments;

    public TodoSimpleResponseDto(Long id, String todo, User user, LocalDateTime modifiedAt, List<CommentDto> comments) {
        this.id = id;
        this.todo = todo;
        this.user = user;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
