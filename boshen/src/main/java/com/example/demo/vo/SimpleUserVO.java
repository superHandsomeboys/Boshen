package com.example.demo.vo;

import lombok.Data;

/**
 * 作者的简单信息
 */
@Data
public class SimpleUserVO {

    private String userName;
    //图片路径
    private String avatarUrl;

    //简介
    private String introduce;

}
