package com.sparta.round13.service;


import com.sparta.round13.dto.TodoSaveRequestDto;
import com.sparta.round13.dto.TodoResponseDto;
import com.sparta.round13.dto.TodoSimpleResponseDto;
import com.sparta.round13.dto.TodoUpdateRequestDto;
import com.sparta.round13.entity.Todo;
import com.sparta.round13.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoResponseDto saveTodo(TodoSaveRequestDto todoSaveRequestDto) {

        // todo 에 RequestDto 정보 담아주기
        Todo todo = new Todo(
                todoSaveRequestDto.getTodo(),
                todoSaveRequestDto.getUsername(),
                todoSaveRequestDto.getPassword()
        );

        // 레퍼지토리에 저장
        todoRepository.save(todo);

        // ResponseDto 로 반환
        return new TodoResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getUsername(),
                todo.getPassword(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }


    public TodoResponseDto getDetailTodo(Long todoId) {

        // todoId 로 Repository 에서 해당 정보 찾아오기
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("찾으시는 글(Todo)이 없습니다."));

        return new TodoResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getUsername(),
                todo.getPassword(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    public List<TodoSimpleResponseDto> getAllSchedule() {

        // 레퍼지토리에서 Todo 를 가져와서 List 에 저장
        List<Todo> todoList = todoRepository.findAllByOrderByModifiedAtDesc();

        // dto 를 위한 List 생성
        List<TodoSimpleResponseDto> dtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            TodoSimpleResponseDto dto = new TodoSimpleResponseDto(
                    todo.getId(),
                    todo.getTodo(),
                    todo.getUsername(),
                    todo.getModifiedAt()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public TodoResponseDto updateTodo(Long todoId,TodoUpdateRequestDto todoUpdateRequestDto) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("찾으시는 글(Todo)이 없습니다."));

        todo.updateTodo(todoUpdateRequestDto.getTodo(), todoUpdateRequestDto.getUsername());

        return new TodoResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getUsername(),
                todo.getPassword(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }
}
