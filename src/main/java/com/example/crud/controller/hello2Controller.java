package com.example.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class hello2Controller {
    @GetMapping("/hello2")
    public String hello2(@RequestParam(value = "name", required = false, defaultValue = "World") String name
                         , @RequestParam(value = "age", required = false, defaultValue = "0") int age
                        , @RequestParam(value = "email", required = false, defaultValue = "0") String email
                        , Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("email", email);

        return "hello2";
    }
}
