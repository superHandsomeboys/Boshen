package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.NewsCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NewsCategoryMapperTest {
    @Autowired
    NewsCategoryMapper newsCategoryMapper;

    @Test
    void addNewsCategory() {
        NewsCategory n = new NewsCategory();
        n.setNewsCategoryName("水能源");
        newsCategoryMapper.addNewsCategory(n);
    }

    @Test
    void deleteNewsCategory() {
        newsCategoryMapper.deleteNewsCategory(1);
    }

    @Test
    void findAllNewsCategory() {
        System.out.println(newsCategoryMapper.findAllNewsCategory());
    }

    @Test
    void findByName() {
        System.out.println(newsCategoryMapper.findByName("新闻"));
    }
}