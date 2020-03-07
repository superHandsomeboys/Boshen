package com.example.demo.service;

import com.example.demo.dto.CompanyDTO;

public interface CompanyService {

    /**
     * 查询公司详细信息
     * @return
     */
    public CompanyDTO findById(int i);
}
