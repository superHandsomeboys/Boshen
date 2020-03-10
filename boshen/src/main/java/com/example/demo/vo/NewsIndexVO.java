package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private Object data;
}
