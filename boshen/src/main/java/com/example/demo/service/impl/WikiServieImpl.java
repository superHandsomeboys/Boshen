package com.example.demo.service.impl;

import com.example.demo.dto.WikiDTO;
import com.example.demo.dynamic.Data;
import com.example.demo.entity.Wiki;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.WikiMapper;
import com.example.demo.service.WikiServie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WikiServieImpl implements WikiServie{

    @Autowired
    WikiMapper wikiMapper;
    @Autowired
    ArticleMapper articleMapper;

    @Override
    @Transactional
    public int deleteWiki(int id) {
        try {
            //1.删除文章
            articleMapper.deleteArticle(wikiMapper.getByIdWiki(id).getArticleId());
            //2.删除wiki
            return wikiMapper.deleteWiki(id);
        }catch (Exception e){
            throw new RuntimeException("wiki删除失败：" + e.getMessage());
        }
    }

    @Override
    public List<WikiDTO> findByPageTitle(String title, int page) {
        String t = "%" + title + "%";
        int start =(page-1) * Data.QUANTITY_IN_PAGE;
        return toDTOS(wikiMapper.findByPageTitle(t,start,Data.QUANTITY_IN_PAGE));
    }

    @Override
    public int findPageQuantityByTitle(String title) {
        String t = "%" + title + "%";
        int size = wikiMapper.findByTitle(t).size();
        if (size == 0){
            return 0;
        }else if (size % Data.QUANTITY_IN_PAGE == 0){   //刚好整页
            return size / Data.QUANTITY_IN_PAGE;
        }
        return size / Data.QUANTITY_IN_PAGE +1;
    }

    @Override
    public WikiDTO findByid(int id) {
        return toDTO(wikiMapper.getByIdWiki(id));
    }

    /**
     * 工具方法
     */
    public List<WikiDTO> toDTOS (List<Wiki> wikis){
        List<WikiDTO> wikiDTOS = new ArrayList<WikiDTO>();
        for (Wiki wiki : wikis){
            WikiDTO wikiDTO = new WikiDTO();
            BeanUtils.copyProperties(wiki,wikiDTO);
            wikiDTO.setArticle(articleMapper.findArticleById(wiki.getArticleId()));
            wikiDTOS.add(wikiDTO);
        }
        return wikiDTOS;
    }

    public WikiDTO toDTO(Wiki wiki){
        WikiDTO wikiDTO = new WikiDTO();
        BeanUtils.copyProperties(wiki,wikiDTO);
        wikiDTO.setArticle(articleMapper.findArticleById(wiki.getArticleId()));
        return wikiDTO;
    }
}
