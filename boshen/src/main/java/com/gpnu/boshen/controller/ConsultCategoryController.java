package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.ConsultCategory;
import com.gpnu.boshen.enums.CommentStateEnum;
import com.gpnu.boshen.enums.ConsultCategoryStateEnum;
import com.gpnu.boshen.mapper.ConsultCategoryMapper;
import com.gpnu.boshen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsultCategoryController {

    @Autowired
    private ConsultCategoryMapper consultCategoryMapper;

    /**
     * 查询所有咨询类别
     * @return
     */
    @GetMapping("/consult/categories")
    public ResultVo list() {
        return new ResultVo(ConsultCategoryStateEnum.SUCCESS,consultCategoryMapper.list()) ;
    }

    /**
     * 添加咨询类别
     * @param category
     * @return
     */
    @PostMapping("/consult/category")
    public ResultVo insert(@RequestBody ConsultCategory category) {
        if (category.getConsultCategoryName() == null){
            return new ResultVo(ConsultCategoryStateEnum.FAIL_NULL_PARAM);
        }
        consultCategoryMapper.insert(category);
        return new ResultVo(ConsultCategoryStateEnum.SUCCESS);
    }

    /**
     * 根据id 查询咨询类别
     * @param id
     * @return
     */
    @GetMapping("/consult/category/{id}")
    public ResultVo findById(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        ConsultCategory consultCategory = consultCategoryMapper.get(id);
        return new ResultVo(ConsultCategoryStateEnum.SUCCESS,consultCategory);
    }

    /**
     * 改询咨询类别
     */
    @PutMapping("/consult/category")
    public ResultVo update(@RequestBody ConsultCategory category){
        if (category.getConsultCategoryName() == null || category.getConsultCategoryId() == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        consultCategoryMapper.update(category);
        return new ResultVo(ConsultCategoryStateEnum.SUCCESS);
    }

    /**
     * 删除咨询类别
     * @param id
     * @return
     */
    @DeleteMapping("/consult/category/{id}")
    public ResultVo delete(@PathVariable("id") Integer id) {
        if (id == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        consultCategoryMapper.delete(id);
        return new ResultVo(ConsultCategoryStateEnum.SUCCESS);
    }
}
