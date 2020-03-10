package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 添加科学文章的请求数据
 */
@Data
public class ScienceRDTO {

    private Integer scienceId;

    private String title;

    private Integer authorId;

    private String content;

    private MultipartFile image;

    private String introduce;
}
