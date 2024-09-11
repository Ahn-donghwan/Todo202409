package com.sparta.round13.controller;

import com.sparta.round13.dto.TodoSaveRequestDto;
import com.sparta.round13.dto.TodoResponseDto;
import com.sparta.round13.dto.TodoSimpleResponseDto;
import com.sparta.round13.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 게시글 작성
    @PostMapping("/api/todos")
    public TodoResponseDto saveTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto){
        return todoService.saveTodo(todoSaveRequestDto);
    }

    // 단건 조회
    @GetMapping("/api/todos/{todoId}")
    public TodoResponseDto getDetailTodo(@PathVariable Long todoId){
        return todoService.getDetailTodo(todoId);
    }

    // 전체 목록 조회
    @GetMapping("/api/todos")
    public List<TodoSimpleResponseDto> getAllSchedule(){
        return todoService.getAllSchedule();
    }
}
