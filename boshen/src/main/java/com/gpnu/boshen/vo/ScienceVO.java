package com.gpnu.boshen.vo;

import com.gpnu.boshen.entity.Article;
import com.gpnu.boshen.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class ScienceVO {

    private Integer scienceId;

    private String title;

    private SimpleUserVO author;

    private Article article;

    private String imageUrl;

    private Integer commentQuantity;

    private Date createTime;
}
