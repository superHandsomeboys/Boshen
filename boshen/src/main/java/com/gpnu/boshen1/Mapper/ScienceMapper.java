package com.gpnu.boshen1.Mapper;

import com.gpnu.boshen1.Bean.Science;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ScienceMapper {

    @Select("select * from science")
    public List<Science> getAllScience();

    @Select("select * from science where science_id=#{science_id}")
    public Science getScienceById(Integer science_id);

    @Delete("delete from science where science_id=#{science_id}")
    public int deleteScience(Integer science_id);

    @Options(useGeneratedKeys = true,keyProperty = "science_id")
    @Insert("insert into science(title,author_id,article_id,create_time) values(#{title},#{author_id},#{article_id},#{create_time})")
    public int insertScience(Science science);

    @Update("update science set title=#{title},author_id=#{author_id},article_id=#{article_id},create_time=#{create_time} where science_id=#{science_id}")
    public int updateScience(Science science);
}
