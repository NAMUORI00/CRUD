package com.example.crud.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private long id;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime created_at; // 생성일시
    private LocalDateTime updated_at; // 수정일시
}
