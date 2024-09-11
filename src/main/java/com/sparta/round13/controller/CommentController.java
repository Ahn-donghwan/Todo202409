package com.sparta.round13.controller;

import com.sparta.round13.dto.CommentSaveRequestDto;
import com.sparta.round13.dto.CommentResponseDto;
import com.sparta.round13.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos/{todoId}/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 저장
    @PostMapping
    public CommentResponseDto saveComment(@PathVariable Long todoId, @RequestBody CommentSaveRequestDto commentSaveRequestDto){
        return commentService.saveComment(todoId, commentSaveRequestDto);
    }

    // 댓글 전체 조회
    @GetMapping
    public List<CommentResponseDto> getAllComment(@PathVariable Long todoId){
        return commentService.getAllComment(todoId);
    }
}
