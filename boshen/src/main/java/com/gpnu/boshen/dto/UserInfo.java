package com.gpnu.boshen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.multipart.MultipartFile;

/**
 * 前端传入数据
 */
@Data
public class UserInfo {
//    @JsonProperty("name")
    private Integer userId;

    private MultipartFile avatar;
}
