package com.gpnu.boshen.service;

import com.gpnu.boshen.entity.NewsCategory;
import com.gpnu.boshen.vo.ResultVo;

public interface NewsCategoryService {
    //增加新闻分类
    public ResultVo addNewsCategory(NewsCategory newsCategory);

    //删除新闻分类
    public ResultVo deleteNewsCategory(Integer newsCategoryId);

    //查找所有的新闻类
    public ResultVo findAllNewsCategory();
}
