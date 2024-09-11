package com.sparta.round13.service;


import com.sparta.round13.dto.TodoSaveRequestDto;
import com.sparta.round13.dto.TodoSaveResponseDto;
import com.sparta.round13.entity.Todo;
import com.sparta.round13.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoSaveResponseDto saveTodo(TodoSaveRequestDto todoSaveRequestDto) {
        Todo todo = new Todo(
                todoSaveRequestDto.getTodo(),
                todoSaveRequestDto.getUsername(),
                todoSaveRequestDto.getPassword()
        );

        todoRepository.save(todo);

        return new TodoSaveResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getUsername(),
                todo.getPassword(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }


}
