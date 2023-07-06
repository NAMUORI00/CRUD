package com.example.crud.controller;

import com.example.crud.dto.Board;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class boardController {

    @Autowired
    private com.example.crud.repository.BoardRepository BoardRepository;

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

    @GetMapping({"/form"})
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id == null){
            model.addAttribute("board", new Board());
        }else {
            Board board = BoardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping({"/form"})
    public String postForm(@ModelAttribute Board board) {
        BoardRepository.save(board);
        return "redirect:/board/list";
    }

    @PostMapping({"/board/delete"})
    public String delete(@RequestParam Long id) {
        BoardRepository.deleteById(id);
        return "redirect:/board/list";
    }

}

