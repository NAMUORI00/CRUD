package com.example.crud.service;

import com.example.crud.model.Board;
import com.example.crud.model.Users;
import com.example.crud.repository.BoardRepository;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.ByteArrayDecoder;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(String username ,Board board) {
            Users users = userRepository.findByUsername(username);
            board.setBoard_author(users);
        return boardRepository.save(board);
    }


}
