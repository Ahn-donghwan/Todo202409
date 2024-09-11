package com.sparta.round13.dto;

import lombok.Getter;

@Getter
public class TodoSaveRequestDto {

    private String todo;
    private String username;
    private Long password;
}
