package com.example.demo.mapper;

import com.example.demo.entity.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyMapper {

    @Select("select * from company")
    public List<Company> getAllCompany();

    @Select("select * from company where company_id=#{companyId}")
    public Company getCompanyById(Integer companyId);

//    @Delete("delete from company where company_id=#{companyId}")
//    public int deleteCompanyById(Integer companyId);

//    @Options(useGeneratedKeys = true,keyProperty = "companyId")
//    @Insert("insert into company(introduce,customer_quantity,member_quantity,project_quantity,to_company_quantity,consult_index_id,train_index_id) values(#{introduce},#{customerQuantity},#{memberQuantity},#{projectQuantity},#{toCompanyQuantity},#{consultIndexId},#{trainIndexId})")
//    public int insertCompany(Company company);

    @Update("update company set introduce=#{introduce},customer_quantity=#{customerQuantity},member_quantity=#{memberQuantity},project_quantity=#{projectQuantity},to_company_quantity=#{toCompanyQuantity},consult_index_id=#{consultIndexId} where company_id=#{companyId}")
    public int updateCompany(Company company);
}
