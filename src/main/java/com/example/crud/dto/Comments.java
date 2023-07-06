package com.example.crud.dto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comments {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    private long board_id;
    private long author_id;
    private String content;
    private String created_at;
    private String updated_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Users author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Board board;

}
