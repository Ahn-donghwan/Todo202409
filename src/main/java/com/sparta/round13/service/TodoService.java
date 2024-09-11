package com.sparta.round13.service;


import com.sparta.round13.dto.TodoDto.TodoRequestDto.TodoDeleteRequestDto;
import com.sparta.round13.dto.TodoDto.TodoRequestDto.TodoSaveRequestDto;
import com.sparta.round13.dto.TodoDto.TodoRequestDto.TodoUpdateRequestDto;
import com.sparta.round13.dto.TodoDto.TodoResponseDto.TodoResponseDto;
import com.sparta.round13.dto.TodoDto.TodoResponseDto.TodoSimpleResponseDto;
import com.sparta.round13.entity.Todo;
import com.sparta.round13.exception.NoSuchResourceException;
import com.sparta.round13.exception.UnAuthorizedAccessException;
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

    public Todo findTodoById(Long todoId){
        return todoRepository.findById(todoId).orElseThrow(() -> new NoSuchResourceException("해당 리소스를 찾을 수 없습니다. ID :" + todoId ));
    }

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
        Todo todo = findTodoById(todoId);

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
    public TodoResponseDto updateTodo(Long todoId, TodoUpdateRequestDto todoUpdateRequestDto) {

        Todo todo = findTodoById(todoId);

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

    @Transactional
    public void deleteTodo(Long todoId, TodoDeleteRequestDto todoDeleteRequestDto) {
        Todo todo = findTodoById(todoId);

        if(!todoDeleteRequestDto.getPassword().equals(todo.getPassword())){
            throw new UnAuthorizedAccessException("접근 거부 (비밀번호가 틀렸습니다.) ");
        }

        todoRepository.deleteById(todoId);
    }
}
