package com.sparta.round13.controller;

import com.sparta.round13.dto.TodoSaveRequestDto;
import com.sparta.round13.dto.TodoResponseDto;
import com.sparta.round13.dto.TodoSimpleResponseDto;
import com.sparta.round13.dto.TodoUpdateRequestDto;
import com.sparta.round13.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // [Lv1] 게시글 작성
    @PostMapping("/api/todos")
    public TodoResponseDto saveTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto){
        return todoService.saveTodo(todoSaveRequestDto);
    }

    // [Lv2] 단건 조회
    @GetMapping("/api/todos/{todoId}")
    public TodoResponseDto getDetailTodo(@PathVariable Long todoId){
        return todoService.getDetailTodo(todoId);
    }

    // [Lv3] 전체 목록 조회
    @GetMapping("/api/todos")
    public List<TodoSimpleResponseDto> getAllSchedule(){
        return todoService.getAllSchedule();
    }

    // [Lv4] 선택한 일정 수정
    @PutMapping("/api/todos/{todoId}")
    public TodoResponseDto UpdateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto todoUpdateRequestDto){
        return todoService.updateTodo(todoId, todoUpdateRequestDto);
    }
}
