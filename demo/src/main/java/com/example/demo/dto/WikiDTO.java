package com.example.demo.dto;

import com.example.demo.entity.Article;
import lombok.Data;

@Data
public class WikiDTO {
    private Integer wikiId;

    private String title;

    private Article article;
}
