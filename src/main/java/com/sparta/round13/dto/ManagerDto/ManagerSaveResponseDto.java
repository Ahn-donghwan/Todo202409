package com.sparta.round13.dto.ManagerDto;

import lombok.Getter;

@Getter
public class ManagerSaveResponseDto {

    private final Long id;

    public ManagerSaveResponseDto(Long id) {
        this.id = id;
    }
}
