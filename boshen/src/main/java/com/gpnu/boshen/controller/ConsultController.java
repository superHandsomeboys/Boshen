package com.gpnu.boshen.controller;

import com.gpnu.boshen.dto.ConsultDTO;
import com.gpnu.boshen.dto.ConsultInfo;
import com.gpnu.boshen.dto.ConsultInsert;
import com.gpnu.boshen.entity.Article;
import com.gpnu.boshen.entity.Consult;
import com.gpnu.boshen.enums.ConsultCategoryStateEnum;
import com.gpnu.boshen.enums.ConsultStateEnum;
import com.gpnu.boshen.mapper.ArticleMapper;
import com.gpnu.boshen.mapper.ConsultMapper;
import com.gpnu.boshen.service.ConsultService;
import com.gpnu.boshen.vo.ConsultVO;
import com.gpnu.boshen.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConsultController {

    @Autowired
    ConsultMapper consultMapper;

    @Autowired
    ConsultService consultService;

    @Autowired
    ArticleMapper articleMapper;

    /**
     * 查询所有咨询类
     * @return
     */
    @GetMapping("/consults")
    public ResultVo list() {
        return new ResultVo(ConsultStateEnum.SUCCESS,consultMapper.list());
    }

    /**
     * 添加咨询类
     * @param
     * @return
     */
    @PostMapping("/consult")
    public ResultVo insert(@RequestBody ConsultInsert consultInsert) {
        Article article = new Article();
        article.setContent(consultInsert.getContent());
        articleMapper.addArticle(article);
        Consult consult = new Consult();
        consult.setArticleId(article.getArticleId());
        BeanUtils.copyProperties(consultInsert,consult);
        consultMapper.insert(consult);
        return new ResultVo(ConsultStateEnum.SUCCESS) ;
    }

    /**
     * 根据id查询咨询
     * @param id
     * @param
     * @return
     */
    @GetMapping("/consult/{id}")
    public ResultVo toEditPage(@PathVariable("id") int id) {

        return new ResultVo(ConsultStateEnum.SUCCESS,consultService.findByConsultId(id));
    }

    /**
     * 更新咨询类
     * @param consultInsert
     * @return
     */
    @PutMapping("/consult")
    public ResultVo update(@RequestBody ConsultInsert consultInsert) {
        //1.拿到咨询类的文章id
        int articleId = consultMapper.get(consultInsert.getConsultId()).getArticleId();
        //2.更新文章
        Article article = new Article();
        article.setContent(consultInsert.getContent());
        article.setArticleId(articleId);
        articleMapper.updateArticle(article);
        //3.更新咨询
        Consult consult = new Consult();
        BeanUtils.copyProperties(consultInsert,consult);
        consult.setArticleId(articleId);
        consultMapper.update(consult);
        return new ResultVo(ConsultStateEnum.SUCCESS);
    }

    /**
     * 删除咨询类
     * @param id
     * @return
     */
    @DeleteMapping("/consult/{id}")
    public ResultVo delete(@PathVariable("id") Integer id) {
        if (id == null){
            return new ResultVo(ConsultStateEnum.FAIL_NULL_PARAM);
        }
        consultService.delete(id);
        return new ResultVo(ConsultStateEnum.SUCCESS);
    }

    /**
     * 根据title模糊查询
     */

    /**
     * 根据类别id查询咨询
     */
    @GetMapping("/consult/categoryId/{id}")
    public ResultVo findByCategoryId(@PathVariable("id") Integer categoryId){
        if (categoryId == null){
            return new ResultVo(ConsultCategoryStateEnum.FAIL_NULL_PARAM);
        }
        List<ConsultDTO> consultDTOS = consultService.findByCategoryId(categoryId);
        List<ConsultVO> consultVOS = new ArrayList<ConsultVO>();
        for (ConsultDTO consultDTO : consultDTOS){
            ConsultVO consultVO = new ConsultVO();
            BeanUtils.copyProperties(consultDTO,consultVO);
            consultVO.setContent(consultDTO.getArticle().getContent());
            consultVOS.add(consultVO);
        }
        return new ResultVo(ConsultCategoryStateEnum.SUCCESS,consultVOS);
    }

    /**
     * 分页，条件查询，科技文章
     */
    @GetMapping("/consults/title/{title}/page/{page}")
    public ResultVo selectConsultByTitlePage(@PathVariable("page") int page,@PathVariable("title") String title){
        Map<String,Object> map = new HashMap<>();
        map.put("consults",consultService.findByPageTitle(title,page));
        map.put("pageQuantity",consultService.findPageQuantityByTitle(title));
        return new ResultVo(ConsultStateEnum.SUCCESS,map);
    }

}
