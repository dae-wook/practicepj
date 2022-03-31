package com.example.practicepj.article.controller;


import com.example.practicepj.article.ArticleService;
import com.example.practicepj.article.CommentService;
import com.example.practicepj.article.entity.Article;
import com.example.practicepj.article.model.ArticleDto;
import com.example.practicepj.article.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("/article/main")
    public String boardMain(Model m) {

        List<Article> list = articleService.list();
        List<ArticleDto> dtoList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            dtoList.add(ArticleDto.of(list.get(i)));
            int count = commentService.list(dtoList.get(i).getId()).size();
            dtoList.get(i).setCommentCount(count);

        }

        m.addAttribute("list", dtoList);

        return "article/main";
    }

    @GetMapping("/article/write.do")
    public String write() {


        return "article/write";
    }

    @PostMapping("article/delete.do")
    public String delete(Long id) {

        articleService.delete(id);

        return "redirect:/article/main";
    }

    @PostMapping("/article/write.do")
    public String write(String title, String content, Principal principal) {

        articleService.write(title,content,principal.getName());
        Long currentArticle = articleService.currentArticle(title,content);

        return "redirect:/article/detail.do?articleId=" + currentArticle;
    }

    @GetMapping("/article/detail.do")
    public String detail(Model m, Long articleId) {

        List<Comment> commentList = commentService.list(articleId);
        Article article = articleService.selectArticle(articleId);

        if(article != null && commentList != null) {
            m.addAttribute("article", article);
            m.addAttribute("commentList", commentList);
        }
        //댓글도 추가해야함
        return "article/detail";

    }

    @PostMapping("/article/writeComment.do")
    public String writeComment(Model m, String comment,Long articleId, Principal principal) {

        String writer = principal.getName();
        boolean result = commentService.write(articleId, comment, writer);
        m.addAttribute("result", result);

        return "redirect:/article/detail.do?articleId=" + articleId;
    }

    @PostMapping("/article/deleteComment.do")
    public String deleteComment(Long articleId, Long commentId) {

        boolean result = commentService.delete(commentId);

        return "redirect:/article/detail.do?articleId=" + articleId;
    }
}
