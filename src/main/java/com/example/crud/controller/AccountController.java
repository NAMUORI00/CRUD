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

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @PostMapping("/register")
    public String register(Users users) {
        userService.save(users);

        return "redirect:/";
    }

    @GetMapping("/register")
    public String getregister(Users users) {
        return "account/register";
    }

}
