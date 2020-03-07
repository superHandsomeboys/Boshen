package com.gpnu.boshen.vo;

import com.gpnu.boshen.entity.Article;
import com.gpnu.boshen.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class SimpleScienceVO {

    private Integer scienceId;

    private String title;

    private String imageUrl;

    private String introduce;

    private Integer commentQuantity;

    private Date createTime;
}
