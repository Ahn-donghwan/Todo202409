package com.sparta.round13.dto;

import lombok.Getter;

@Getter
public class CommentSaveRequestDto {

    private String username;    // 작성 유저명
    private String contents;    // 댓글 내용
}
