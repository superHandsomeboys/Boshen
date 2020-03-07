package com.example.demo.vo;

import com.example.demo.entity.NewsCategory;
import lombok.Data;

import java.util.List;

/**
 * 新闻资讯页数据
 */
@Data
public class NewsInfornationVO {
    //最多5个新闻动态
    private List<SimpleNewsVO> dynamicNewsList;

    //最多6个轮播图新闻
    private List<SimpleNewsVO> sliderNewsList;

    //第一个类别名
    private String firstCategory;

    //第一个类别名的3个新闻
    private List<SimpleNewsVO> fiestNewsList;

    //第二个类别名
    private String secondCategory;

    //第二个类别名的3个新闻
    private List<SimpleNewsVO> secondNewsList;

    //其他类的8个新闻
    private List<SimpleNewsVO> otherNewsList;

    //全部个类别
    private List<NewsCategory> categoryList;

    //热门新闻
    private List<SimpleNewsVO> hotNewsList;

    //最新新闻
    private List<SimpleNewsVO> newNewsList;
}
