package com.example.crud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private boolean enabled;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    // 엔티티 업데이트 전에 호출되는 메서드
    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "author")
    private List<Board> boards;

    @OneToMany(mappedBy = "author")
    private List<Comments> comments;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

}
