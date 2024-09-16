package com.sparta.round13.controller;

import com.sparta.round13.dto.TodoDto.TodoRequestDto.TodoDeleteRequestDto;
import com.sparta.round13.dto.TodoDto.TodoRequestDto.TodoRequestDto;
import com.sparta.round13.dto.TodoDto.TodoResponseDto.TodoNewsfeedDto;
import com.sparta.round13.dto.TodoDto.TodoResponseDto.TodoResponseDto;
import com.sparta.round13.dto.TodoDto.TodoResponseDto.TodoWithCommentResponseDto;
import com.sparta.round13.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/todos")
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // [Lv1] 게시글 작성
    @PostMapping
    public ResponseEntity<TodoResponseDto> saveTodo(@RequestBody TodoRequestDto todoRequestDto) {
        return ResponseEntity.ok(todoService.saveTodo(todoRequestDto));
//        ok 안하고 created 로 시도
//        return ResponseEntity.created(null).body(null);
    }

    // [Lv2] 단건 조회
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoWithCommentResponseDto> getDetailTodo(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.getDetailTodo(todoId));
    }

    // [Lv3] 전체 목록 조회
    @GetMapping
    public ResponseEntity<List<TodoWithCommentResponseDto>> getAllTodo() {
        return ResponseEntity.ok(todoService.getAllTodo());
    }

    // [Lv4] 선택한 일정 수정
    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto todoRequestDto) {
        return ResponseEntity.ok(todoService.updateTodo(todoId, todoRequestDto));
    }

    // [Lv5] 선택한 일정 삭제
    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable Long todoId, @RequestBody TodoDeleteRequestDto todoDeleteRequestDto) {
        todoService.deleteTodo(todoId, todoDeleteRequestDto);
    }

    // Todo 뉴스피드를 페이지 네이션을 활용하여 구현 (version.1)
    @GetMapping("/v1/newsfeed")
    public Page<TodoNewsfeedDto> getTodoNewsfeed(@RequestParam(defaultValue = "1", name = "page") int page, @RequestParam(defaultValue = "10") int size) {
        return todoService.getTodoNewsfeed(page, size);
    }

    // Todo 뉴스피드 (version.2)
    @GetMapping("/v2/newsfeed")
    public Page<TodoNewsfeedDto> getTodoNewsfeed(
            @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return todoService.getTodoNewsfeed(pageable);
    }
}

