package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private Integer commentQuantity;

    private String introduce;

}
