package com.example.practicepj.article;

import com.example.practicepj.article.entity.Article;
import com.example.practicepj.article.entity.Comment;
import com.example.practicepj.article.repository.ArticleRepository;
import com.example.practicepj.article.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Override
    public boolean write(Long articleId, String comment, String writer) {



        Comment com = Comment.builder()
                .article(getArticle(articleId))
                .content(comment)
                .writer(writer)
                .regDt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy년 MM월 dd일 HH시 mm분")))
                .build();

        commentRepository.save(com);

        return true;
    }

    @Override
    public boolean delete(Long commentId) {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setDeleteYn(true);
            commentRepository.save(comment);
            return true;
        }

        return false;
    }

    @Override
    public List<Comment> list(Long articleId) {

//        Article article = articleRepository.findById(articleId).get();
        List<Comment> list = null;
        Article article;
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if(optionalArticle.isPresent()) {
            article = optionalArticle.get();
            list = commentRepository.findAllByArticle(article);
            for(int i = 0; i < list.size(); i++) {
                if(list.get(i).isDeleteYn() == true) {
                    list.remove(i);
                }
            }
        }

        return list;
    }

    public Article getArticle(Long articleId) {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        Article article = null;

        if(optionalArticle.isPresent()) {
            article = optionalArticle.get();
            return article;
        }

        return null;
    }
}
