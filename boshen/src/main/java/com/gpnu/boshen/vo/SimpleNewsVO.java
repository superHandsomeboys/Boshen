package com.gpnu.boshen.vo;

import lombok.Data;

import java.util.Date;

/**
 * 简易的新闻信息
 */
@Data
public class SimpleNewsVO<T> {

    private Integer newsId;

    private String title;

    private String imageUrl;

    private Date createTime;

    private Integer commentQuantity;

    private String introduce;

}
