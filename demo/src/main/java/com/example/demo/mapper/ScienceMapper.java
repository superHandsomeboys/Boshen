package com.example.demo.mapper;

import com.example.demo.entity.Science;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScienceMapper {

    @Select("select * from science")
    public List<Science> getAllScience();

    @Select("select * from science where science_id=#{scienceId}")
    public Science getScienceById(Integer science_id);

    @Delete("delete from science where science_id=#{scienceId}")
    public int deleteScience(Integer science_id);

    @Options(useGeneratedKeys = true,keyProperty = "scienceId")
    @Insert("insert into science(title,author_id,article_id,create_time,image_url,introduce,comment_quantity) " +
            "values(#{title},#{authorId},#{articleId},#{createTime},#{imageUrl},#{introduce},#{commentQuantity})")
    public int insertScience(Science science);

    @Update("update science set title=#{title},author_id=#{authorId},article_id=#{articleId},create_time=#{createTime}," +
            "image_url=#{imageUrl},introduce=#{introduce},comment_quantity=#{commentQuantity}  where science_id=#{scienceId}")
    public int updateScience(Science science);

    @Select("select * from science limit #{start},#{quantity}")
    public List<Science> findLimit(@Param("start") int start, @Param("quantity") int quantity);

    @Select("select * from science where title like #{title} limit #{start},#{quantity}")
    public List<Science> findByPageTitle(@Param("title") String title, @Param("start") int start, @Param("quantity") int quantity);

    @Select("select * from science where title like #{title}")
    public List<Science> findByTitle(String title);

    @Select("select * from science where author_id = #{authorId}")
    public List<Science> findByAuthorId(int authorId);
}



