package com.example.demo.dto;

import com.example.demo.entity.ConsultCategory;
import lombok.Data;

@Data
public class CompanyDTO {
    private Integer companyId;

    private String introduce;

    private Integer customerQuantity;

    private Integer memberQuantity;

    private Integer projectQuantity;

    private Integer toCompanyQuantity;

    private ConsultCategory indexConsult;

}
