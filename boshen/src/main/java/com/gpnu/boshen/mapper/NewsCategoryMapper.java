package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.News;
import com.gpnu.boshen.entity.NewsCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsCategoryMapper {

    /**
     * 增加新闻类别
     * @param newsCategory
     * @return
     */
    @Insert("insert into news_category (news_category_name) values(#{newsCategoryName})")
    public int addNewsCategory(NewsCategory newsCategory);

    /**
     * 删除新闻类别
     * @param newsCategoryId
     * @return
     */
    @Delete("delete from news_category where news_category_id=#{newsCategoryId}")
    public int deleteNewsCategory(Integer newsCategoryId);

    @Update("update news_category set news_category_name = #{newsCategoryName} where news_category_id = #{newsCategoryId}")
    public int updateNewsCategory(NewsCategory newsCategory);

    /**
     * 查找所有的新闻类别
     * 限制返回数量，语句后加 limit 2,3
     * 返回代表下标2开始的3个元素
     * @return
     */
    @Select("select * from news_category")
    public List<NewsCategory> findAllNewsCategory();

    /**
     * 根据新闻类别名查找新闻类别类
     * @param newsCategoryName
     * @return
     */
    @Select("select * from news_category where news_category_name=#{newsCategoryName}")
    public NewsCategory findByName(String newsCategoryName);

    /**
     * 用id查新闻类别类
     * @param id
     * @return
     */
    @Select("select * from news_category where news_category_id = #{newsCategoryName}")
    public NewsCategory findById(int id);

    /**
     * 查前i个类别
     * @param i
     * @return
     */
    @Select("select * from news_category limit 0,#{i}")
    public List<NewsCategory> findLimit(int i);


}
