package com.example.demo.service.impl;

import com.example.demo.entity.NewsCategory;
import com.example.demo.enums.NewsCategoryStateEnum;
import com.example.demo.mapper.NewsCategoryMapper;
import com.example.demo.service.NewsCategoryService;
import com.example.demo.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsCategoryServiceImpl implements NewsCategoryService{
    @Autowired
    NewsCategoryMapper newsCategoryMapper;

    @Override
    public ResultVo addNewsCategory(NewsCategory newsCategory) {
        //查看是否重合类型
        NewsCategory newsCategory1 = newsCategoryMapper.findByName(newsCategory.getNewsCategoryName());
        if (newsCategory1 != null){
            //发现重合
            return new ResultVo(NewsCategoryStateEnum.FAIL_REPEAT_NAME);
        }
        //未重合，添加操作
        int result = newsCategoryMapper.addNewsCategory(newsCategory);
        if(result <=0){
            //添加失败
            return new ResultVo(NewsCategoryStateEnum.FAIL_ADD);
        }
        //成功
        return new ResultVo(NewsCategoryStateEnum.SUCCESS);
    }

    @Override
    public ResultVo deleteNewsCategory(Integer newsCategoryId) {
        int result = newsCategoryMapper.deleteNewsCategory(newsCategoryId);
        if (result <=0){
            //没该类型
            return new ResultVo(NewsCategoryStateEnum.FAIL_NULL);
        }
        //成功
        return new ResultVo(NewsCategoryStateEnum.SUCCESS);
    }

    @Override
    public List<NewsCategory> findAllNewsCategory() {
        List<NewsCategory> list = newsCategoryMapper.findAllNewsCategory();
        return list;
    }

    @Override
    public List<NewsCategory> findLimit(int i) {

        return newsCategoryMapper.findLimit(i);
    }
}
