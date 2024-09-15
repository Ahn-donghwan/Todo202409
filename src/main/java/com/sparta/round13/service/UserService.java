package com.sparta.round13.service;

import com.sparta.round13.dto.userDto.UserDto;
import com.sparta.round13.dto.userDto.UserRequestDto;
import com.sparta.round13.dto.userDto.UserResponseDto;
import com.sparta.round13.entity.User;
import com.sparta.round13.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        // dto 로 부터 정보를 가져옴
        User newUser = new User(userRequestDto.getUsername(), userRequestDto.getEmail());

        // dto 의 정보를 db에 저장
        User savedUser = userRepository.save(newUser);

        // dto 로 정보를 반환
        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();

        List<UserResponseDto> dtoList = new ArrayList<>();

        for (User user : userList) {
            UserResponseDto dto = new UserResponseDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    public UserResponseDto getDetailUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        user.updateUser(userRequestDto.getUsername(), userRequestDto.getEmail());

        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }


    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));
        userRepository.deleteById(userId);
    }
}
