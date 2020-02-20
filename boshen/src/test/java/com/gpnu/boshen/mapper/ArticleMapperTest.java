package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.Article;
import com.gpnu.boshen.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleMapperTest {
    @Autowired
    ArticleMapper articleMapper;

    @Test
    void addArticle() {
        Article article = new Article();
        article.setContent("卡付款的时间和你卡时间的话可记得开会就开始啦回家看了");
        articleMapper.addArticle(article);
        System.out.println(article);
    }

    @Test
    void deleteArticle() {
        articleMapper.deleteArticle(1);
    }

    @Test
    void updateArticle() {
        Article article = new Article();
        article.setArticleId(1);
        article.setContent("把把把把把把把把把阿布");
        articleMapper.updateArticle(article);
    }

    @Test
    void findArticle() {
        System.out.println(articleMapper.findArticleById(1));
    }
}