package com.example.demo.entity;

import lombok.Data;

@Data
public class Company {
    private Integer companyId;

    private String introduce;

    private Integer customerQuantity;

    private Integer memberQuantity;

    private Integer projectQuantity;

    private Integer toCompanyQuantity;

    private Integer consultIndexId;

//    private Integer trainIndexId;

}
