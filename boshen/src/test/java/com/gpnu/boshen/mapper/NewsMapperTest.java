package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.News;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NewsMapperTest {

    @Autowired
    NewsMapper newsMapper;

    @Test
    void addNews() {
        News news = new News();
        news.setArticleId(1);
        news.setAuthorId(1);
        news.setCategoryId(6);
        news.setCreateTime(new Date());
        news.setImageUrl("test");
        news.setIsSlider(true);
        news.setTitle("helloest");
        newsMapper.addNews(news);
    }

    @Test
    void deleteNews() {
        newsMapper.deleteNews(2);
    }


    @Test
    void findByNews() {
        News news = new News();
//        news.setNewsId(1);
//        news.setAuthorId(1);
//        news.setCategoryId(6);
//        news.setIsSlider(true);
        news.setTitle("%hello%");
        System.out.println(newsMapper.findByNews(news));
    }

    @Test
    void findByPage() {

        System.out.println(newsMapper.findByPage("%hello%",1,5));
    }

    @Test
    void updateIsSlider(){
        News news = new News();
        news.setNewsId(1);
        news.setIsSlider(false);
        newsMapper.updateIsSlider(news);
    }
}