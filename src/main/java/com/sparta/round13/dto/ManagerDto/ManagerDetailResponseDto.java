package com.sparta.round13.dto.ManagerDto;

import com.sparta.round13.dto.userDto.UserDto;
import com.sparta.round13.entity.User;
import lombok.Getter;

@Getter
public class ManagerDetailResponseDto {

    private final Long id;
    private final User user;

    public ManagerDetailResponseDto(Long id, User user) {
        this.id = id;
        this.user = user;
    }
}
