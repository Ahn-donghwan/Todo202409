package com.sparta.round13.service;


import com.sparta.round13.dto.commentDto.CommentDto;
import com.sparta.round13.dto.TodoDto.TodoRequestDto.TodoDeleteRequestDto;
import com.sparta.round13.dto.TodoDto.TodoRequestDto.TodoRequestDto;
import com.sparta.round13.dto.TodoDto.TodoResponseDto.TodoNewsfeedDto;
import com.sparta.round13.dto.TodoDto.TodoResponseDto.TodoResponseDto;
import com.sparta.round13.dto.TodoDto.TodoResponseDto.TodoSimpleResponseDto;
import com.sparta.round13.entity.Todo;
import com.sparta.round13.entity.User;
import com.sparta.round13.exception.NoSuchResourceException;
import com.sparta.round13.exception.UnAuthorizedAccessException;
import com.sparta.round13.repository.CommentRepository;
import com.sparta.round13.repository.TodoRepository;
import com.sparta.round13.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Todo findTodoById(Long todoId){
        return todoRepository.findById(todoId).orElseThrow(() -> new NoSuchResourceException("해당 리소스를 찾을 수 없습니다. ID :" + todoId ));
    }

    @Transactional
    public TodoResponseDto saveTodo(TodoRequestDto todoRequestDto) {
        User user = userRepository.findById(todoRequestDto.getUserId()).orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        // todo 에 RequestDto 정보 담아주기
        Todo todo = new Todo(
                todoRequestDto.getTodo(),
                user,
                todoRequestDto.getPassword()
        );

        // 레퍼지토리에 저장
        todoRepository.save(todo);

        // ResponseDto 로 반환
        return new TodoResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getUser(),
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
                todo.getUser(),
                todo.getPassword(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    public List<TodoSimpleResponseDto> getAllTodo() {

        // 레퍼지토리에서 Todo 를 가져와서 List 에 저장
        List<Todo> todoList = todoRepository.findAllByOrderByModifiedAtDesc();

        // dto 를 위한 List 생성
        List<TodoSimpleResponseDto> dtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            List<CommentDto> commentList = commentRepository.findByTodoId(todo.getId());

            TodoSimpleResponseDto dto = new TodoSimpleResponseDto(
                    todo.getId(),
                    todo.getTodo(),
                    todo.getUser(),
                    todo.getModifiedAt(),
                    commentList
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public TodoResponseDto updateTodo(Long todoId, TodoRequestDto todoRequestDto) {
        User user = userRepository.findById(todoRequestDto.getUserId()).orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        Todo todo = findTodoById(todoId);

        todo.updateTodo(todoRequestDto.getTodo(), todo.getUser());

        return new TodoResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getUser(),
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


    public Page<TodoNewsfeedDto> getTodoNewsfeed(int page, int size) {

        Pageable pageable = PageRequest.of(page-1, size);
        // page-1 : index 는 0 부터 시작하지만, 우리가 보는 page 자체는 1 부터 시작하기 때문

        Page<Todo> todoPage = todoRepository.findAllByOrderByModifiedAtDesc(pageable);

        // page 는 for 문을 쓸 수 없기 때문에 map 을 써야 한다.
        // for 문은 return 이 없지만, map 은 해당 타입으로 return 을 해주는
        // converter 같은 역할을 수행한다고 보면 된다.


        // return todoPage.map(TodoNewsfeedDto::new);
        // TodoNewsfeedDto 에 생성자를 구현해주면 위처럼 람다식으로 간결하게 표현 가능
        return todoPage.map(todo -> new TodoNewsfeedDto(
                todo.getTodo(),
                todo.getUser(),
                todo.getCreatedAt(),
                todo.getModifiedAt(),
                commentRepository.countByTodoId(todo.getId())
        ));
    }

    public Page<TodoNewsfeedDto> getTodoNewsfeed(Pageable pageable) {

        Page<Todo> todoPage = todoRepository.findAllByOrderByModifiedAtDesc(pageable);

        // return todoPage.map(TodoNewsfeedDto::new);
        // 람다?
        return todoPage.map(todo -> new TodoNewsfeedDto(
                todo.getTodo(),
                todo.getUser(),
                todo.getCreatedAt(),
                todo.getModifiedAt(),
                commentRepository.countByTodoId(todo.getId())
        ));
    }
}
