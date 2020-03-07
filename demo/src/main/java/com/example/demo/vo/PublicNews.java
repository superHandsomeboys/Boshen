package com.example.demo.vo;

import lombok.Data;

import java.util.List;

/**
 * 上栏公共部分新闻资讯
 */
@Data
public class PublicNews {

    private String category;

    private List<NewsIndexVO> newsIndexVOs;

}
