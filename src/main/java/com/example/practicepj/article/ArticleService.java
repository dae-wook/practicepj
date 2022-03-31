package com.example.practicepj.article;

import com.example.practicepj.article.entity.Article;

import java.util.List;

public interface ArticleService {


    boolean write(String title, String content, String writer);
    List list();
    Article selectArticle(Long articleId);
    Long currentArticle(String title,String content);
    boolean delete(Long id);

}
