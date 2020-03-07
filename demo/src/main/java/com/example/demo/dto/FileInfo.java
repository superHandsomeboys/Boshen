package com.example.demo.dto;

import lombok.Data;

@Data
public class FileInfo {
    //前缀路径  /user/local/，用于映射文件夹
    private String frontPath;

    //后缀路径  authorid/xxx.jpg,用于定位文件
    private String backPath;
}
