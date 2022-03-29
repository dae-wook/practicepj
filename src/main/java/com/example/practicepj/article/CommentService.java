package com.example.practicepj.article;

import com.example.practicepj.article.entity.Comment;
import java.util.List;

public interface CommentService {



    boolean write(Long articleId, String comment, String writer);
    List<Comment> list(Long articleId);


}
