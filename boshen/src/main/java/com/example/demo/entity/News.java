package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 新闻
 */
@Data
public class News {
    private Integer newsId;
    //新闻标题
    private String title;

    //新闻附带的图片
    private String imageUrl;

    //新闻内容
    private Integer articleId;

    //新闻作者
    private Integer authorId;

    //新闻类别
    private Integer categoryId;

    //是否为轮播图
    private Boolean isSlider;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    //新闻简介
    private String introduce;

    //评论数量
    private Integer commentQuantity;
}
