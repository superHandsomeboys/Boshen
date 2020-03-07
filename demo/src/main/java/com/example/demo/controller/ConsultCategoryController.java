package com.example.demo.controller;

import com.example.demo.entity.ConsultCategory;
import com.example.demo.enums.CommentStateEnum;
import com.example.demo.enums.ConsultCategoryStateEnum;
import com.example.demo.enums.ConsultStateEnum;
import com.example.demo.mapper.ConsultCategoryMapper;
import com.example.demo.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询最多7个类别，id,name
     */
    @GetMapping("/consult/public")
    public ResultVo findpublic(){
        return new ResultVo(ConsultStateEnum.SUCCESS,consultCategoryMapper.findAllLimit(0,7));
    }



}
