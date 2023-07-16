package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {
    @RequestMapping("/menu")
    public String menu(Model model) {
        return "menu"; // menu.html 파일을 반환합니다.
    }

}
