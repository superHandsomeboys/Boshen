package com.gpnu.boshen1;


import com.gpnu.boshen1.Bean.Company;
import com.gpnu.boshen1.Bean.CompanyMember;
import com.gpnu.boshen1.Mapper.CompanyMemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

@SpringBootTest
public class CompanyMemberTest {
    @Autowired
    CompanyMemberMapper companyMemberMapper;

    //查找所有
    @Test
    void FindAll(){
        List<CompanyMember> companyMembers = companyMemberMapper.getAllCompanyMember();
        for(CompanyMember companyMember:companyMembers){
            System.out.println(companyMember);
        }
    }
    //根据id查找
    @Test
    void FindById(){
        CompanyMember companyMember = companyMemberMapper.getById(5);
        System.out.println(companyMember);
    }
    //插入数据
    @Test
    void InsertCompanyMember()throws Exception{

        CompanyMember Companymember = new CompanyMember();
        Companymember.setMember_name("bbbb");
        Companymember.setIntroduce("she is a girl");
        Companymember.setPosition("ceo");

        System.out.println(companyMemberMapper.insertCompanyMember(Companymember));
    }
    //删除
    @Test
    void DeleteCompany(){
        System.out.println(companyMemberMapper.deleteCompanyMember(2));
    }
    //更新
    @Test
    void UpdateCompany(){
        CompanyMember Companymember = new CompanyMember();
        Companymember.setMember_id(2);
        Companymember.setMember_name("ccc");
        Companymember.setIntroduce("shesdf is a girl");
        Companymember.setPosition("总经理");
        System.out.println(companyMemberMapper.updateCompanyMember(Companymember));
    }

}
