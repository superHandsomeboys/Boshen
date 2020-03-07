package com.example.demo.dto;

import lombok.Data;

@Data
public class ConsultInsert {
    private Integer consultId;

    private String consultTitle;

    private String content;

    private Integer categoryId;
}
