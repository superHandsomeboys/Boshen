package com.gpnu.boshen.service.impl;

import com.gpnu.boshen.entity.News;
import com.gpnu.boshen.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NewsServiceImplTest {

    @Autowired
    NewsService newsService;

    @Test
    void addNews() {
    }

    @Test
    void deleteNews() {
        newsService.deleteNews(3);
    }

    @Test
    void findByNews() {
        News news = new News();
//        news.setAuthorId(1);
//        news.setTitle("hello");
        news.setIsSlider(true);
        System.out.println(newsService.findByNews(news));
    }

    @Test
    void findByPage() {
        System.out.println(newsService.findByPage("est",1,2));
    }

    @Test
    void findDetailedNewsVOByNewsId(){
        System.out.println(newsService.findDetailedNewsVOByNewsId(5));
    }
}