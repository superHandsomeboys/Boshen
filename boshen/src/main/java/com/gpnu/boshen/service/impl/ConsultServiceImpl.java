package com.gpnu.boshen.service.impl;

import com.gpnu.boshen.dto.ConsultDTO;
import com.gpnu.boshen.dynamic.Data;
import com.gpnu.boshen.entity.Consult;
import com.gpnu.boshen.entity.ConsultCategory;
import com.gpnu.boshen.mapper.ArticleMapper;
import com.gpnu.boshen.mapper.ConsultCategoryMapper;
import com.gpnu.boshen.mapper.ConsultMapper;
import com.gpnu.boshen.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultServiceImpl implements ConsultService{
    @Autowired
    ConsultMapper consultMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ConsultCategoryMapper consultCategoryMapper;

    @Override
    public List<ConsultDTO> findByCategoryId(int id) {
        return toDTOs(consultMapper.findByCategory(id));
    }

    @Override
    public List<ConsultDTO> findByPageTitle(String title, int page) {
        String t = "%" + title + "%";
        int start =(page-1) * Data.QUANTITY_IN_PAGE;
        return toDTOs(consultMapper.findByPageTitle(t,start,Data.QUANTITY_IN_PAGE));
    }

    @Override
    public int findPageQuantityByTitle(String title) {
        String t = "%" + title + "%";
        int size = consultMapper.findByTitle(t).size();
        if (size == 0){
            return 0;
        }else if (size % Data.QUANTITY_IN_PAGE == 0){   //刚好整页
            return size / Data.QUANTITY_IN_PAGE;
        }
        return size / Data.QUANTITY_IN_PAGE +1;
    }

    @Transactional
    @Override
    public void delete(int id) {
        try{
            //1.删除文章
            articleMapper.deleteArticle(consultMapper.get(id).getArticleId());
            //2.删除咨询
            consultMapper.delete(id);
        }catch (Exception e){
            throw new RuntimeException("删除咨询错误："+ e.getMessage());
        }
    }

    @Override
    public ConsultDTO findByConsultId(int consultId) {

        return toDTO(consultMapper.get(consultId));
    }

    /**
     * 工具方法
     */
    public List<ConsultDTO> toDTOs(List<Consult> consults){
        List<ConsultDTO> consultDTOS = new ArrayList<ConsultDTO>();
        for (Consult consult : consults ){
            ConsultDTO consultDTO = new ConsultDTO();
            consultDTO.setConsultId(consult.getConsultId());
            consultDTO.setTitle(consult.getConsultTitle());
            consultDTO.setArticle(articleMapper.findArticleById(consult.getArticleId()));
            consultDTO.setConsultCategory(consultCategoryMapper.get(consult.getCategoryId()));
            consultDTOS.add(consultDTO);
        }
        return consultDTOS;
    }

    public ConsultDTO toDTO(Consult consult){
        ConsultDTO consultDTO = new ConsultDTO();
        consultDTO.setConsultId(consult.getConsultId());
        consultDTO.setTitle(consult.getConsultTitle());
        consultDTO.setArticle(articleMapper.findArticleById(consult.getArticleId()));
        consultDTO.setConsultCategory(consultCategoryMapper.get(consult.getCategoryId()));
        return consultDTO;
    }
}
