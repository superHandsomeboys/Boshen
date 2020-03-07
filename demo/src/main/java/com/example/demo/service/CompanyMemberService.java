package com.example.demo.service;

import com.example.demo.dto.CompanyMemberDTO;
import com.example.demo.entity.CompanyMember;

import java.util.List;

public interface CompanyMemberService {
    /**
     * 添加
     */
    int addMember(CompanyMemberDTO companyMemberDTO);

    /**
     * 改动
     */
    int updateMember(CompanyMemberDTO companyMemberDTO);

    /**
     * 删除
     */
    int deleteMember(int id);

    /**
     * 根据id查
     */
    CompanyMember findMemberById(int id);

    /**
     * 查询所有
     */
    List<CompanyMember> findAll();
 }
