package com.example.demo.mapper;

import com.example.demo.entity.News;
import com.example.demo.mapper.dynamicSql.NewsDS;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsMapper {

    /**
     * 插入数据
     * @param news
     * @return
     */
    @Insert("insert into news (author_id,title,image_url,article_id,category_id,create_time,is_slider,introduce,comment_quantity)" +
            "values (#{authorId},#{title},#{imageUrl},#{articleId},#{categoryId},#{createTime},#{isSlider},#{introduce},#{commentQuantity})")
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
    public List<News> findByPage(@Param("title") String title, @Param("pageStart") int pageStart,
                                 @Param("quantity") int quantity);

    @Select("select * from news where title like #{title}")
    public List<News> findByTitle(String title);

    /**
     * 查询前i条新闻
     * @param i
     * @return
     */
    @Select("select * from news limit 0,#{i}")
    public List<News> findLimit(Integer i);

    /**
     * 查询所有新闻
     * @return
     */
    @Select("select * from news")
    public List<News> findAll();

    /**
     * 用类别id查询前i个新闻
     * @param categoryId
     * @param i
     * @return
     */
    @Select("select * from news where category_id = #{categoryId} limit 0,#{i}")
    public List<News> findByCategoryId(@Param("categoryId") int categoryId, @Param("i") int i);

    /**
     * 查询最热的新闻前i 个新闻
     * @param i
     * @return
     */
    @Select("select * from news order by comment_quantity desc limit 0,#{i}")
    public List<News> findHotest(int i);

    /**
     * 查询最新的前i个新闻
     * @param i
     * @return
     */
    @Select("select * from news order by create_time desc limit 0,#{i}")
    public List<News> findNewest(int i);

    @Select("select * from news limit #{start},#{quantity}")
    public List<News> findLimit01(@Param("start") int start, @Param("quantity") int quantity);

    @Select("select * from news where category_id = #{categoryId} limit #{start},#{quantity}")
    public List<News> findByCategoryId01(@Param("categoryId") int categoryId, @Param("start") int start, @Param("quantity") int quantity);

    /**
     * 更新新闻
     */
    @Update("update news set author_id=#{authorId},title = #{title},image_url = #{imageUrl}," +
            "article_id = #{articleId},category_id = #{categoryId},create_time = #{createTime}," +
            "is_slider = #{isSlider},introduce = #{introduce},comment_quantity = #{commentQuantity} " +
            "where news_id = #{newsId}")
    public int updateNews(News news);
}
