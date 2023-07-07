package com.example.crud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String created_at;
    private String updated_at;
    private String role;

    @OneToMany(mappedBy = "author")
    private List<Board> boards;

    @OneToMany(mappedBy = "author")
    private List<Comments> comments;

}
