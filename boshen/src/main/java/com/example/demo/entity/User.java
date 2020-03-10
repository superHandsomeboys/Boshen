package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

//@Entity 是jpa的注解
@Data
public class User {
    private Integer userId;
    private String userName;
    private String password;
    private String mail;
    private String phone;
    private String avatar;	//头像
    private Integer userType;  //(1是普通用户，2是公司发布文章员，3是超级管理员)
    private String introduce;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

}
