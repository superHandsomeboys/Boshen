package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
