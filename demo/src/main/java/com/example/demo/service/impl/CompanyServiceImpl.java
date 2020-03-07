package com.example.demo.service.impl;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.entity.Company;
import com.example.demo.mapper.*;
import com.example.demo.service.CompanyService;
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
