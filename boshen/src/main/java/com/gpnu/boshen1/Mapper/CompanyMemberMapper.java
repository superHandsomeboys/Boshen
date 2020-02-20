package com.gpnu.boshen1.Mapper;

import com.gpnu.boshen1.Bean.CompanyMember;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface CompanyMemberMapper {

    @Select("select * from company_member")
    public List<CompanyMember> getAllCompanyMember();

    @Select("select * from company_member where member_id=#{member_id}")
    public CompanyMember getById(Integer member_id);

    @Delete("delete from company_member where member_id=#{member_id}")
    public int deleteCompanyMember(Integer member_id);

    @Options(useGeneratedKeys = true,keyProperty = "member_id")
    @Insert("insert into company_member(member_name,position,introduce,avatar) values(#{member_name},#{position},#{introduce},#{avatar})")
    public int insertCompanyMember(CompanyMember companyMember);

    @Update("update company_member set member_name=#{member_name},position=#{position},introduce=#{introduce},avatar=#{avatar} where member_id=#{member_id}")
    public int updateCompanyMember(CompanyMember companyMember);
}
