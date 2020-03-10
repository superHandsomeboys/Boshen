package com.example.demo.dto;

import lombok.Data;

/**
 * 文章详情页中的作者简介
 */
@Data
public class AuthorInfo {

    private String authorName;

    private String introduce;

    private String avatarUrl;
}
