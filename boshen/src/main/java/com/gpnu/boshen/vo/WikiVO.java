package com.gpnu.boshen.vo;

import com.gpnu.boshen.entity.Article;
import lombok.Data;

@Data
public class WikiVO {
    private Integer wikiId;

    private String title;

    private String content;
}
