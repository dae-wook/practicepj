package com.example.practicepj.article.repository;

import com.example.practicepj.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByTitleAndContent(String title, String content);
}
