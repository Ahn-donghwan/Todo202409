package com.sparta.round13.controller;

import com.sparta.round13.dto.ManagerDto.ManagerDetailResponseDto;
import com.sparta.round13.dto.ManagerDto.ManagerSaveRequestDto;
import com.sparta.round13.dto.ManagerDto.ManagerSaveResponseDto;
import com.sparta.round13.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/api/todos/{todoId}/managers")
    public ManagerSaveResponseDto saveManager(
            @PathVariable Long todoId,
            @RequestBody ManagerSaveRequestDto managerSaveRequestDto){
        return managerService.saveManager(todoId, managerSaveRequestDto);
    }

    // manager 전체 조회 Version 1
    @GetMapping("/api/todos/{todoId}/v1/managers")
    public List<ManagerDetailResponseDto> getMembersV1(@PathVariable Long todoId){
        return managerService.getManagersV1(todoId);
    }

//    // manager 전체 조회 Version 2
//    @GetMapping("/api/todos/{todoId}/v1/managers")
//    public List<ManagerDetailResponseDto> getMembersV2(@PathVariable Long todoId){
//        return managerService.getManagersV2(todoId);
//    }
}
