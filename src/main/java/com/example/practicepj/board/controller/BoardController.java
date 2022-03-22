package com.example.practicepj.board.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @GetMapping("/board/main")
    public String boardMain() {

        return "board/main";
    }

    @GetMapping("/board/write.do")
    public String write() {

        return "board/write";
    }
    @PostMapping("/board/write.do")
    public String write(String title, String content) {

        System.out.println(title);
        System.out.println(content);

        return "board/main";
    }
}
