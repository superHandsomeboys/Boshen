package com.example.demo.dto;

import com.example.demo.entity.Article;
import com.example.demo.entity.ConsultCategory;
import lombok.Data;

@Data
public class ConsultDTO {

    private Integer consultId;

    private String title;

    private Article article;

    private ConsultCategory consultCategory;
}
