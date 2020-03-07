package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Science {
//    DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.UK);
    private Integer scienceId;

    private String title;

    private Integer authorId;

    private Integer articleId;

    private String imageUrl;

    private String introduce;

    private Integer commentQuantity;

    private Date createTime;
}
