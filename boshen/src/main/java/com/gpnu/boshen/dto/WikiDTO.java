package com.gpnu.boshen.dto;

import com.gpnu.boshen.entity.Article;
import lombok.Data;

@Data
public class WikiDTO {
    private Integer wikiId;

    private String title;

    private Article article;
}
