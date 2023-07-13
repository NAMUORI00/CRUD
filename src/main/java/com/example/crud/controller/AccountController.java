package com.example.crud.controller;

import com.example.crud.model.Users;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    // 유저서비스 기능
    @Autowired
    private UserService userService;

    // 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    // 회원가입
    @PostMapping("/register")
    public String register(Users users) {
        userService.save(users);

        return "redirect:/";
    }

    // 회원가입 페이지
    @GetMapping("/register")
    public String getregister(Users users) {
        return "account/register";
    }

}
