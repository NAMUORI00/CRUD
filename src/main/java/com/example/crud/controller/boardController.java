package com.example.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class boardController {
    @GetMapping({"/board", "/board/list"})
    public String board() {
        return "board/list";
    }
}

