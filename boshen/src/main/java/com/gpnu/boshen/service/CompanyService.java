package com.gpnu.boshen.service;

import com.gpnu.boshen.dto.CompanyDTO;

public interface CompanyService {

    /**
     * 查询公司详细信息
     * @return
     */
    public CompanyDTO findById(int i);
}
