package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        // 모델에 필요한 데이터를 추가하는 로직을 여기에 작성합니다.
        // 예를 들어, model.addAttribute("message", "Hello, World!");

        return "index"; // index.html 파일의 이름을 반환합니다.
    }
}
