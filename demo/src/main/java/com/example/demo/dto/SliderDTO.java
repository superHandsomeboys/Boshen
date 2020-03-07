package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SliderDTO {
    private Integer id;

    private String name;

    //type为0代表关于我们页，为1代表首页
    private Integer type;

    private MultipartFile image;
}
