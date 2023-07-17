package com.example.crud.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comments {
    // 프라머리 키
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) // 데이터 생성시 자동증가
    private long id;
    private long board_id;
    private long author_id;
    private String content;
    private String created_at;
    private String updated_at;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계, 패치타입은 레이지
    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false) // 조인컬럼, 참조컬럼, insertable, updatable
    private Users comments_author; // 유저 테이블과 조인

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Board board;

}
