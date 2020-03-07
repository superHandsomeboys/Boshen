package com.gpnu.boshen.dto;

import com.gpnu.boshen.entity.Article;
import com.gpnu.boshen.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class ScienceDTO {

    private Integer scienceId;

    private String title;

    private User author;

    private Article article;

    private String imageUrl;

    private String introduce;

    private Integer commentQuantity;

    private Date createTime;
}
