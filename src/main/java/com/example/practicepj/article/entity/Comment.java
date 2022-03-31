package com.example.practicepj.article.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;
    private String writer;
    private boolean deleteYn;

    @ManyToOne // 댓글 여러개가 게시글 하나에 종속됨.
    @JoinColumn(name = "article_id")
    private Article article;

    private String regDt;

}
