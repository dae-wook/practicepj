package com.example.practicepj.article;

import com.example.practicepj.article.entity.Article;
import com.example.practicepj.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    @Override
    public boolean write(String title, String content, String writer) {

        Article article = Article.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .regDt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy년 MM월 dd일 HH시 mm분")))
                .deleteYn(false)
                .build();

        articleRepository.save(article);

        return true;
    }

    @Override
    public List list() {

        List<Article> list = articleRepository.findAll();
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).isDeleteYn() == true) {
                list.remove(i);
            }
        }
        return list;
    }

    @Override
    public Article selectArticle(Long articleId) {

        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        Article article = new Article();
        if(optionalArticle.isPresent()) {
            article = optionalArticle.get();
        }

        return article;
    }

    @Override
    public Long currentArticle(String title, String content) {

        Optional<Article> optionalArticle = articleRepository.findByTitleAndContent(title, content);
        Long currentArticle = optionalArticle.get().getId();
        return currentArticle;
    }

    @Override
    public boolean delete(Long id) {

        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setDeleteYn(true);
            articleRepository.save(article);
            return true;
        }

        return false;
    }
}
