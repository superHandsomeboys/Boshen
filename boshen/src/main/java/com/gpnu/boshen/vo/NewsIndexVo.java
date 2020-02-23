package com.gpnu.boshen.vo;

import lombok.Data;

/**
 * 首页的新闻必含数据,可根据需要的其他数据动态添加data
 */
@Data
public class NewsIndexVo {
    private Integer newsId;

    private String imageUrl;

    private String title;

    private String createTime;

    private Object data;
}
