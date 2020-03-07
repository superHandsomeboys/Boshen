package com.example.demo.service.impl;

import com.example.demo.dto.ConsultDTO;
import com.example.demo.dynamic.Data;
import com.example.demo.entity.Consult;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.ConsultCategoryMapper;
import com.example.demo.mapper.ConsultMapper;
import com.example.demo.service.ConsultService;
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
