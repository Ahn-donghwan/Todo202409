package com.sparta.round13.service;


import com.sparta.round13.dto.TodoSaveRequestDto;
import com.sparta.round13.dto.TodoResponseDto;
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
}
