package com.sparta.round13.service;

import com.sparta.round13.dto.ManagerDto.ManagerDetailResponseDto;
import com.sparta.round13.dto.ManagerDto.ManagerSaveRequestDto;
import com.sparta.round13.dto.ManagerDto.ManagerSaveResponseDto;
import com.sparta.round13.entity.Manager;
import com.sparta.round13.entity.Todo;
import com.sparta.round13.entity.User;
import com.sparta.round13.repository.ManagerRepository;
import com.sparta.round13.repository.TodoRepository;
import com.sparta.round13.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;


    @Transactional
    public ManagerSaveResponseDto saveManager(Long todoId, ManagerSaveRequestDto managerSaveRequestDto) {

        // todoRepository 에서 todo 를 가져온다. 없으면 예외 처리
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        // 등록하려고 하는 user 가 todo 를 만든 todo 인지 확인하고 가져온다. 아니라면 예외처리
        User user = userRepository.findById(managerSaveRequestDto.getTodoUserId())
                .orElseThrow(() -> new NullPointerException("Todo 를 만든 User 가 아닙니다."));

        if (!(user.getId() != null && ObjectUtils.nullSafeEquals(user.getId(), todo.getUser().getId()))){
            throw new NullPointerException("Manager 로 지정하려고 하는 User 가 Todo 를 만든 User 가 아닙니다.");
        }

        // 지정하려고 하는 Manager 가 실제로 존재하는 User 인지를 확인해서 가져오고, 아니라면 예외처리
        User manager = userRepository.findById(managerSaveRequestDto.getManagerUserId())
                .orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));


        Manager newManager = new Manager(manager, todo);
        Manager savedManager = managerRepository.save(newManager);

        return new ManagerSaveResponseDto(savedManager.getId());
    }

    public List<ManagerDetailResponseDto> getManagersV1(Long todoId) {

        // 실제로 있는 todo 인지를 확인
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        // 해당 todoId 로  manager 를 조회
        List<Manager> managerList = managerRepository.findByTodoId(todo.getId());

        List<ManagerDetailResponseDto> dtoList = new ArrayList<>();

        for (Manager manager : managerList) {
            ManagerDetailResponseDto dto = new ManagerDetailResponseDto(
                    manager.getId(),
                    manager.getUser());
            dtoList.add(dto);
        }
        return dtoList;
    }

//    public List<ManagerDetailResponseDto> getManagersV2(Long todoId) {
//        // 실제로 있는 todo 인지를 확인
//        Todo todo = todoRepository.findById(todoId)
//                .orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));
//
//        // 해당 todoId 로  manager 를 조회
//        List<Manager> managerList = managerRepository.findByTodoId(todo.getId());
//
//        return managerList.stream().map(manager -> new ManagerDetailResponseDto(
//                manager.getId(),
//                manager.getUser()
//        )).collect(Collectors.toList());
//    }
}
