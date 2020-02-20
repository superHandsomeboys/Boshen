package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.News;
import com.gpnu.boshen.mapper.dynamicSql.NewsDS;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsMapper {

    /**
     * 插入数据
     * @param news
     * @return
     */
    @Insert("insert into news (author_id,title,image_url,article_id,category_id,create_time,is_slider)" +
            "values (#{authorId},#{title},#{imageUrl},#{articleId},#{categoryId},#{createTime},#{isSlider})")
    public int addNews(News news);

    /**
     * 删除新闻
     * @param newsId
     * @return
     */
//    @Delete("delete from news where news_id = #{newsId}")
    @DeleteProvider(type = NewsDS.class,method = "delete")
    public int deleteNews(int newsId);

    /**
     * 设置新闻是否放置轮播图处
     * @param news
     * @return
     */
    @Update("update news set is_slider = #{isSlider} where news_id = #{newsId}")
    public int updateIsSlider(News news);

    /**
     * 用newsid,作者id,类型id，是否为轮播图，标题的模糊化，查询news
     * @param news
     * @return
     */
    @SelectProvider(type = NewsDS.class,method = "select")
    public List<News> findByNews(News news);

    /**
     * 有条件的分页查询
     * @param title
     * @param pageStart
     * @param quantity
     * @return
     */
    @Select("select * from news where title like #{title} limit #{pageStart},#{quantity}")
    public List<News> findByPage(@Param("title") String title ,@Param("pageStart") int pageStart,
                                 @Param("quantity") int quantity);
}
