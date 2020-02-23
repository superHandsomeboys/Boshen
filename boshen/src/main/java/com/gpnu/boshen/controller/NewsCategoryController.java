package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.NewsCategory;
import com.gpnu.boshen.enums.NewsCategoryStateEnum;
import com.gpnu.boshen.service.NewsCategoryService;
import com.gpnu.boshen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsCategoryController {

    @Autowired
    NewsCategoryService newsCategoryService;

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
     * 查找所有的新闻类别
     * @return
     */
    @GetMapping("/newsCategory")
    public ResultVo findAllNewCategory(){
        return new ResultVo(NewsCategoryStateEnum.SUCCESS,newsCategoryService.findAllNewsCategory());
    }
}
