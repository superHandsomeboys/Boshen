package com.gpnu.boshen.service;

import com.gpnu.boshen.dto.ConsultDTO;
import com.gpnu.boshen.entity.Consult;

import java.util.List;

public interface ConsultService {

    /**
     * 根据咨询类别id查所有的咨询，并封装到DTO中
     * @return
     */
    public List<ConsultDTO> findByCategoryId(int id);

    /**
     * 条件，分页查询
     */
    public List<ConsultDTO> findByPageTitle(String title, int page);

    /**
     * 条件查询页数
     */
    public int findPageQuantityByTitle(String title);

    /**
     * 删除
     */
    public void delete(int id);

    /**
     * 用id查询
     */
    public ConsultDTO findByConsultId(int consultId);
}
