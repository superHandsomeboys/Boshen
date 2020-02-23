package com.gpnu.boshen.dto;

import lombok.Data;

/**
 * 首页新闻的数据封装类，放newsIndexVO.data中
 */
@Data
public class NewsIndexDTO {
    private String category;

    private String author;

    private String introduce;
}
