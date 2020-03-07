package com.example.demo.mapper;

import com.example.demo.entity.CompanyMember;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyMemberMapper {

    @Select("select * from company_member")
    public List<CompanyMember> getAllCompanyMember();

    @Select("select * from company_member where member_id=#{memberId}")
    public CompanyMember getById(Integer member_id);

    @Delete("delete from company_member where member_id=#{memberId}")
    public int deleteCompanyMember(Integer member_id);

    @Options(useGeneratedKeys = true,keyProperty = "memberId")
    @Insert("insert into company_member(member_name,position,introduce,avatar) values(#{memberName},#{position},#{introduce},#{avatar})")
    public int insertCompanyMember(CompanyMember companyMember);

    @Update("update company_member set member_name=#{memberName},position=#{position},introduce=#{introduce},avatar=#{avatar} where member_id=#{memberId}")
    public int updateCompanyMember(CompanyMember companyMember);
}
