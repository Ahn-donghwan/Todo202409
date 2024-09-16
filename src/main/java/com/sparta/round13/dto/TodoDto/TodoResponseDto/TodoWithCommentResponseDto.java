package com.sparta.round13.dto.TodoDto.TodoResponseDto;

import com.sparta.round13.dto.commentDto.CommentDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TodoWithCommentResponseDto {

    private final Long todoId;
    private final String todo;
    private final Long userId;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private final List<CommentDto> comments;


    public TodoWithCommentResponseDto(Long todoId, String todo, Long userId, String username, LocalDateTime createdAt, LocalDateTime modifiedAt, List<CommentDto> comments) {
        this.todoId = todoId;
        this.todo = todo;
        this.userId = userId;
        this.username = username;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
