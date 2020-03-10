package com.example.demo.vo;

import com.example.demo.entity.Article;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ScienceVO {

    private Integer scienceId;

    private String title;

    private SimpleUserVO author;

    private Article article;

    private String imageUrl;

    private Integer commentQuantity;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
