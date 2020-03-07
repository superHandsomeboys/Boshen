package com.example.demo.vo;

import lombok.Data;

import java.util.List;

@Data
public class NewsIndex04VO {

    private String category;

    private NewsIndexVO leftNews;

    private List<NewsIndexVO> rightNewsList;
}
