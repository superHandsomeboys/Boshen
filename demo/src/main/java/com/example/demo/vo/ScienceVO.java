package com.example.demo.vo;

import com.example.demo.entity.Article;
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

    private Date createTime;
}
