package com.example.demo.dto;

import lombok.Data;

@Data
public class ConsultInfo {
    private Integer consultId;

    private String title;

    private String  content;

    private String consultCategoryName;
}
