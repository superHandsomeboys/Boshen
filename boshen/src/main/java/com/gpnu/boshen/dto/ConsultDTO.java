package com.gpnu.boshen.dto;

import com.gpnu.boshen.entity.Article;
import com.gpnu.boshen.entity.ConsultCategory;
import lombok.Data;

@Data
public class ConsultDTO {

    private Integer consultId;

    private String title;

    private Article article;

    private ConsultCategory consultCategory;
}
