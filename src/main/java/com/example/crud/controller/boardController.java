package com.example.crud.controller;

import com.example.crud.dto.Board;
import com.example.crud.repository.UserRepository;
import com.example.crud.repository.boardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class boardController {

    @Autowired
    private boardRepository BoardRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/board", "/board/list"})
    public String board(Model model){
        List<Board> boards = BoardRepository.findAll();

        List<String> nicknames = new ArrayList<>();  // 작성자 닉네임을 저장할 리스트
        for (Board board : boards) {
            String nickname = board.getAuthor().getNickname();  // 작성자의 닉네임 정보를 가져옴
            nicknames.add(nickname);  // 작성자의 닉네임을 리스트에 추가
        }

        model.addAttribute("boards", boards);
        model.addAttribute("nicknames", nicknames);  // 작성자 닉네임 리스트를 모델에 추가

        return "board/list";
    }
}

