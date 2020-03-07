package com.example.demo.entity;

import lombok.Data;

@Data
public class CompanyMember {
    private Integer memberId;
    private String memberName;
    private String position;
    private String introduce;
    private String avatar;

}
