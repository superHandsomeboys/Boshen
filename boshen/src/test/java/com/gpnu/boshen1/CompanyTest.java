package com.gpnu.boshen1;

import com.gpnu.boshen1.Bean.Company;
import com.gpnu.boshen1.Mapper.CompanyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CompanyTest {
    @Autowired
    CompanyMapper companyMapper;

    //查找所有
    @Test
    void FindAll(){
        List<Company> companies = companyMapper.getAllCompany();
        for(Company company:companies){
            System.out.println(company);
        }
    }
    //根据id查找
    @Test
    void FindById(){
        Company company = companyMapper.getCompanyById(1);
        System.out.println(company);
    }
    //插入数据
    @Test
    void InsertCompany(){
        Company company = new Company();
        company.setIntroduce("博慎博慎博慎");
        company.setCustomer_quantity(1111);
        company.setMember_quantity(2222);
        company.setProject_quantity(3333);
        company.setTo_company_quantity(444);

        System.out.println(companyMapper.insertCompany(company));
    }
    //删除
    @Test
    void DeleteCompany(){
        System.out.println(companyMapper.deleteCompanyById(2));
    }
    @Test
    //更新
    void UpdateCompany(){
        Company company = new Company();
        company.setCompany_id(3);
        company.setCustomer_quantity(55);
        System.out.println(companyMapper.updateCompany(company));
    }
}
