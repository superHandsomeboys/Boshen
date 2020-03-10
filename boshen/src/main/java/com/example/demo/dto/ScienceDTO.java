package com.example.demo.dto;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ScienceDTO {

    private Integer scienceId;

    private String title;

    private User author;

    private Article article;

    private String imageUrl;

    private String introduce;

    private Integer commentQuantity;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
