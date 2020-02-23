package com.gpnu.boshen.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ConsultMapperTest {

    @Autowired
    ConsultMapper consultMapper;

    @Test
    void get() {
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

    @Test
    void findByCategory() {
        System.out.println(consultMapper.findByCategory(1));
    }

    @Test
    void findByTitle() {
    }
}