package com.example.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min=2, max=30, message = "제목은 2자 이상 30자 이하입니다.")
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

    @OneToMany(mappedBy = "board") // 일대다 관계 board 이름으로 테이블 매핑
    private List<Comments> comments;
}
