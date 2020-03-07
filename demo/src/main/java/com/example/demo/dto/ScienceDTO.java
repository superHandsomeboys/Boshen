package com.example.demo.dto;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;
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

    private Date createTime;
}
