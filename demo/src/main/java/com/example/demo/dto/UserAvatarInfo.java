package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 前端传入数据
 */
@Data
public class UserAvatarInfo {
//    @JsonProperty("name")
    private Integer userId;

    private MultipartFile avatar;
}
