package com.example.practicepj.article.repository;

import com.example.practicepj.article.entity.Article;
import com.example.practicepj.article.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByArticle(Article article);
}
