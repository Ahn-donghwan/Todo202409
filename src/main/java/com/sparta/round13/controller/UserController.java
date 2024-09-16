package com.sparta.round13.controller;

import com.sparta.round13.dto.userDto.UserRequestDto;
import com.sparta.round13.dto.userDto.UserResponseDto;
import com.sparta.round13.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users")
    public UserResponseDto saveUser(@RequestBody UserRequestDto userRequestDto){
        return userService.saveUser(userRequestDto);
    }

    // 유저 전체 목록 조회 (v1)
    @GetMapping("/api/v1/users")
    public List<UserResponseDto> getAllUsersV1(){
        return userService.getAllUsersV1();
    }

    // 유저 전체 목록 조회 (v2)
    @GetMapping("/api/v2/users")
    public List<UserResponseDto>  getAllUsersV2(){
        return userService.getAllUsersV2();
    }

    // 유저 단건 조회
    @GetMapping("/api/users/{userId}")
    public UserResponseDto getDetailUser(@PathVariable Long userId){
        return userService.getDetailUser(userId);
    }

    // 유저 정보 수정
    @PutMapping("/api/users/{userId}")
    public UserResponseDto updateUser(@PathVariable Long userId, @RequestBody UserRequestDto userRequestDto){
        return userService.updateUser(userId, userRequestDto);
    }

    // 유저 정보 삭제
    @DeleteMapping("/api/users/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}
