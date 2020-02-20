package com.gpnu.boshen.service.impl;

import com.gpnu.boshen.entity.NewsCategory;
import com.gpnu.boshen.service.NewsCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NewsCategoryServiceImplTest {

    @Autowired
    NewsCategoryService newsCategoryService;

    @Test
    void addNewsCategory() {
        NewsCategory n = new NewsCategory();
        n.setNewsCategoryName("太阳能");
        System.out.println(newsCategoryService.addNewsCategory(n));
    }

    @Test
    void deleteNewsCategory() {
        System.out.println(newsCategoryService.deleteNewsCategory(3));
    }

    @Test
    void findAllNewsCategory() {
        System.out.println(newsCategoryService.findAllNewsCategory());
    }
}