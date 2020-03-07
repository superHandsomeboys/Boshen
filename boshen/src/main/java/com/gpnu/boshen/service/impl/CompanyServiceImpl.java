package com.gpnu.boshen.service.impl;

import com.gpnu.boshen.dto.CommentDTO;
import com.gpnu.boshen.dto.CompanyDTO;
import com.gpnu.boshen.entity.Company;
import com.gpnu.boshen.entity.ConsultCategory;
import com.gpnu.boshen.mapper.*;
import com.gpnu.boshen.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    ConsultCategoryMapper consultCategoryMapper;

    @Override
    public CompanyDTO findById(int i) {
        return toDTO(companyMapper.getCompanyById(1));
    }

    /**
     * 工具类 comment -> commentDTO
     */
    public CompanyDTO toDTO(Company company){
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyId(company.getCompanyId());
        companyDTO.setCustomerQuantity(company.getCustomerQuantity());
        companyDTO.setIntroduce(company.getIntroduce());
        companyDTO.setMemberQuantity(company.getMemberQuantity());
        companyDTO.setProjectQuantity(company.getProjectQuantity());
        companyDTO.setToCompanyQuantity(company.getToCompanyQuantity());
        companyDTO.setIndexConsult(consultCategoryMapper.get(company.getConsultIndexId()));

        return companyDTO;
    }
}
