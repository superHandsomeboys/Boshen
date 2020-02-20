package com.gpnu.boshen1.Mapper;

import com.gpnu.boshen1.Bean.Company;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


public interface CompanyMapper {

    @Select("select * from company")
    public List<Company> getAllCompany();

    @Select("select * from company where company_id=#{company_id}")
    public Company getCompanyById(Integer company_id);

    @Delete("delete from company where company_id=#{company_id}")
    public int deleteCompanyById(Integer company_id);

    @Options(useGeneratedKeys = true,keyProperty = "company_id")
    @Insert("insert into company(introduce,customer_quantity,member_quantity,project_quantity,to_company_quantity,consult_index_id,train_index_id) values(#{introduce},#{customer_quantity},#{member_quantity},#{project_quantity},#{to_company_quantity},#{consult_index_id},#{train_index_id})")
    public int insertCompany(Company company);

    @Update("update company set introduce=#{introduce},customer_quantity=#{customer_quantity},member_quantity=#{member_quantity},project_quantity=#{project_quantity},to_company_quantity=#{to_company_quantity},consult_index_id=#{consult_index_id},train_index_id=#{train_index_id} where company_id=#{company_id}")
    public int updateCompany(Company company);
}
