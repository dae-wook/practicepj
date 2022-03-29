package com.example.practicepj.article;

import com.example.practicepj.article.entity.Article;
import com.example.practicepj.article.entity.Comment;
import com.example.practicepj.article.repository.ArticleRepository;
import com.example.practicepj.article.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                .regDt(LocalDateTime.now())
                .build();

        commentRepository.save(com);

        return true;
    }

    @Override
    public List<Comment> list(Long articleId) {

        Article article = articleRepository.findById(articleId).get();

        List<Comment> list = commentRepository.findAllByArticle(article);

//        if(list != null) {
//            for(int i = 0 ; i < list.size() ; i++) {
//                if(list.get(i).getArticle().getId() != articleId) {
//                    list.remove(i);
//                }
//            }
//            return list;
//        }else {
//            return null;
//        }
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
