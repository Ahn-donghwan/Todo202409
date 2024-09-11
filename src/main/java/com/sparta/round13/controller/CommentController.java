package com.sparta.round13.controller;

import com.sparta.round13.dto.CommentSaveRequestDto;
import com.sparta.round13.dto.CommentResponseDto;
import com.sparta.round13.dto.CommentUpdateRequestDto;
import com.sparta.round13.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 댓글 저장
    @PostMapping("/todos/{todoId}/comments")
    public CommentResponseDto saveComment(@PathVariable Long todoId, @RequestBody CommentSaveRequestDto commentSaveRequestDto){
        return commentService.saveComment(todoId, commentSaveRequestDto);
    }

    // 댓글 전체 조회
    @GetMapping("todos/{todoId}/comments")
    public List<CommentResponseDto> getAllComment(@PathVariable Long todoId){
        return commentService.getAllComment(todoId);
    }

    // 댓글 단건 조회
    @GetMapping("todos/comments/{commentId}")
    public CommentResponseDto getDetailComment(@PathVariable Long commentId){
        return commentService.getDetailComment(commentId);
    }

    // 댓글 수정
    @PutMapping("todos/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto){
        return commentService.updateComment(commentId, commentUpdateRequestDto);
    }


}
