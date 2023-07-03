package com.example.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class userDto {
    private Long id;
    private String username;
    private String userid;
    private String email;
    private String password;
    private String role;
}
