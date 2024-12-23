package ru.highloadJava.lab4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebSocketController {

    @GetMapping("/ui")
    public String chart(Model model) {
        return "ui";
    }
}
