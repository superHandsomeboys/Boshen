package com.example.demo.dto;

import com.example.demo.entity.Article;
import com.example.demo.entity.NewsCategory;
import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class NewsDTO {

    private Integer newsId;
    //新闻标题
    private String title;

    //新闻附带的图片
    private String imageUrl;

    //新闻内容
    private Article article;

    //新闻作者
    private User author;

    //新闻类别
    private NewsCategory category;

    //是否为轮播图
    private Boolean isSlider;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    //新闻简介
    private String introduce;

    //评论数量
    private Integer commentQuantity;
}
