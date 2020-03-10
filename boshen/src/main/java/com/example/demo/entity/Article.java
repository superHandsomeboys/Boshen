package com.example.demo.entity;

import lombok.Data;

/**
 * 文章
 */
@Data
public class Article {
    private Integer articleId;
    /**
     * 文章内容
     */
    private String content;
}
