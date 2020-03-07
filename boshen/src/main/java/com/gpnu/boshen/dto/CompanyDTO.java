package com.gpnu.boshen.dto;

import com.gpnu.boshen.entity.ConsultCategory;
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
