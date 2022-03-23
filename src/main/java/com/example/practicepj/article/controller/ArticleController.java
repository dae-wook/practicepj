package com.example.practicepj.article.controller;


import com.example.practicepj.article.ArticleService;
import com.example.practicepj.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article/main")
    public String boardMain(Model m) {

        List<Article> list = articleService.list();

        m.addAttribute("list", list);

        return "article/main";
    }

    @GetMapping("/article/write.do")
    public String write() {

        return "article/write";
    }
    @PostMapping("/article/write.do")
    public String write(String title, String content, Principal principal) {

        articleService.write(title,content,principal.getName());

        return "redirect:/article/main";
    }

    @GetMapping("/article/detail.do")
    public String detail(Model m, Long articleId) {

        m.addAttribute("article",articleService.selectArticle(articleId));
        //댓글도 추가해야함
        return "article/detail";

    }
}
