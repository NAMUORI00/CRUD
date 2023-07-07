package com.example.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private long author_id;
    private LocalDateTime created_at; // 생성일시
    private LocalDateTime updated_at; // 수정일시

    // 엔티티 저장 전에 호출되는 메서드
    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    // 엔티티 업데이트 전에 호출되는 메서드
    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Users author;

    @OneToMany(mappedBy = "board")
    private List<Comments> comments;
}
