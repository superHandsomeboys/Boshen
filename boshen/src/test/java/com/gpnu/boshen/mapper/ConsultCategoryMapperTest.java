package com.gpnu.boshen.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ConsultCategoryMapperTest {

    @Autowired
    ConsultCategoryMapper consultCategoryMapper;

    @Test
    void get() {
        System.out.println(consultCategoryMapper.get(1));
    }

    @Test
    void list() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}