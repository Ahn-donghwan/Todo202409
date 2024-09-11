package com.sparta.round13.controller;

import com.sparta.round13.dto.*;
import com.sparta.round13.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // [Lv1] 게시글 작성
    @PostMapping("/api/todos")
    public ResponseEntity<TodoResponseDto> saveTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto){
        return ResponseEntity.ok(todoService.saveTodo(todoSaveRequestDto));
    }

    // [Lv2] 단건 조회
    @GetMapping("/api/todos/{todoId}")
    public ResponseEntity<TodoResponseDto> getDetailTodo(@PathVariable Long todoId){
        return ResponseEntity.ok(todoService.getDetailTodo(todoId));
    }

    // [Lv3] 전체 목록 조회
    @GetMapping("/api/todos")
    public ResponseEntity<List<TodoSimpleResponseDto>> getAllSchedule(){
        return ResponseEntity.ok(todoService.getAllSchedule());
    }

    // [Lv4] 선택한 일정 수정
    @PutMapping("/api/todos/{todoId}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto todoUpdateRequestDto){
        return ResponseEntity.ok(todoService.updateTodo(todoId, todoUpdateRequestDto));
    }

    // [Lv5] 선택한 일정 삭제
    @DeleteMapping("/api/todos/{todoId}")
    public void deleteTodo(@PathVariable Long todoId, @RequestBody TodoDeleteRequestDto todoDeleteRequestDto) {
        todoService.deleteTodo(todoId, todoDeleteRequestDto);
    }
}
