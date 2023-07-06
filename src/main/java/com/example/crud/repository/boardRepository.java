package com.example.crud.repository;

import com.example.crud.dto.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface boardRepository extends JpaRepository<Board, Long>{

}
