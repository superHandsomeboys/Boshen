package com.gpnu.boshen1.Mapper;

import com.gpnu.boshen1.Bean.Wiki;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface WikiMapper {

    @Select("select * from wiki")
    public List<Wiki> getAllWiki();

    @Select("select * from wiki where wiki_id=#{wiki_id}")
    public Wiki getByIdWiki(Integer wiki_id);

    @Delete("delete from wiki where wiki_id=#{wiki_id}")
    public int deleteWiki(Integer wiki_id);

    @Options(useGeneratedKeys = true,keyProperty = "wiki_id")
    @Insert("insert into wiki(title,article_id) values(#{title},#{article_id})")
    public int insertWiki(Wiki wiki);

    @Update("update wiki set title=#{title},article_id=#{article_id} where wiki_id=#{wiki_id}")
    public int updateWiki(Wiki wiki);
}
