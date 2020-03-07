package com.example.demo.vo;

import lombok.Data;

import java.util.Date;

/**
 * 首页的新闻必含数据,可根据需要的其他数据动态添加data
 */
@Data
public class NewsIndexVO {
    private Integer newsId;

    private String imageUrl;

    private String title;

    private Date createTime;

    private Object data;
}
