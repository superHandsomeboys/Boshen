package com.gpnu.boshen.controller;

import com.gpnu.boshen.service.NewsCategoryService;
import com.gpnu.boshen.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NewsControllerTest {
    @Autowired
    NewsService newsService;
    @Autowired
    NewsCategoryService newsCategoryService;

    @Test
    void findNewsInformation() {

    }
}