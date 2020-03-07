package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.NewsCategory;
import com.gpnu.boshen.enums.NewsCategoryStateEnum;
import com.gpnu.boshen.mapper.NewsCategoryMapper;
import com.gpnu.boshen.service.NewsCategoryService;
import com.gpnu.boshen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsCategoryController {

    @Autowired
    NewsCategoryService newsCategoryService;
    @Autowired
    NewsCategoryMapper newsCategoryMapper;

    /**
     * 添加新闻类别
     * @param newsCategory
     * @return
     */
    @PostMapping("/newsCategory")
    public ResultVo addNewCatrgory(@RequestBody NewsCategory newsCategory){
        if (newsCategory.getNewsCategoryName() == null){
            return new ResultVo(NewsCategoryStateEnum.FAIL_NULL_PARAM);
        }
         return newsCategoryService.addNewsCategory(newsCategory);
    }

    /**
     * 删除新闻类别
     * @param newsCategoryId
     * @return
     */
    @DeleteMapping("/newsCategory/{id}")
    public ResultVo deleteNewCategory(@PathVariable("id") Integer newsCategoryId){
        if (newsCategoryId == null){
            return new ResultVo(NewsCategoryStateEnum.FAIL_NULL_PARAM);
        }
        return newsCategoryService.deleteNewsCategory(newsCategoryId);
    }

    /**
     * 改新闻类别
     */
    @PutMapping("/newsCategory")
    public ResultVo update(@RequestBody NewsCategory newsCategory){
        if (newsCategory.getNewsCategoryName() == null || newsCategory.getNewsCategoryId() == null){
            return new ResultVo(NewsCategoryStateEnum.FAIL_NULL_PARAM);
        }
        newsCategoryMapper.updateNewsCategory(newsCategory);
        return new ResultVo(NewsCategoryStateEnum.SUCCESS);
    }

    /**
     * 查找所有的新闻类别
     * @return
     */
    @GetMapping("/newsCategory/all")
    public ResultVo findAllNewCategory(){
        return new ResultVo(NewsCategoryStateEnum.SUCCESS,newsCategoryService.findAllNewsCategory());
    }

    /**
     * 根据新闻类别id查
     */
    @GetMapping("newsCategory/{id}")
    public ResultVo findCategoryById(@PathVariable("id") Integer newsCategoryId){
        return new ResultVo(NewsCategoryStateEnum.SUCCESS,newsCategoryMapper.findById(newsCategoryId));
    }
}
