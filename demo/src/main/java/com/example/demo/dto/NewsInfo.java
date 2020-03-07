package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用于添加新闻
 */
@Data
public class NewsInfo {

    //新闻标题
    private String title;

    //新闻附带的图片()
    private MultipartFile image;

    //新闻内容()
    private String article;

    //新闻作者
    private Integer authorId;

    //新闻类别
    private Integer categoryId;

    //新闻简介
    private String introduce;

}
