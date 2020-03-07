package com.gpnu.boshen.dto;

import com.gpnu.boshen.entity.Article;
import com.gpnu.boshen.entity.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 添加科学文章的请求数据
 */
@Data
public class ScienceRDTO {

    private Integer scienceId;

    private String title;

    private Integer authorId;

    private String centent;

    private MultipartFile image;

    private String introduce;
}
