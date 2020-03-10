package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SimpleScienceVO {

    private Integer scienceId;

    private String title;

    private String imageUrl;

    private String introduce;

    private Integer commentQuantity;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
