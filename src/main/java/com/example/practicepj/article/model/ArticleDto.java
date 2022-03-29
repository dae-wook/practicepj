package com.example.practicepj.article.model;

import com.example.practicepj.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ArticleDto {


    private Long id;

    private String title;
    private String content;
    private String writer;

    private int commentCount;
    private LocalDateTime regDt;


    public static ArticleDto of(Article article) {

        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .writer(article.getWriter())
                .regDt(article.getRegDt())
                .build();
    }
}
