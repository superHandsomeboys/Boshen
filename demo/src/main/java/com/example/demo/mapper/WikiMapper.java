package com.example.demo.mapper;

import com.example.demo.entity.Wiki;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WikiMapper {

    @Select("select * from wiki")
    public List<Wiki> getAllWiki();

    @Select("select * from wiki where wiki_id=#{wikiId}")
    public Wiki getByIdWiki(Integer wikiId);

    @Delete("delete from wiki where wiki_id=#{wikiId}")
    public int deleteWiki(Integer wikiId);

    @Options(useGeneratedKeys = true,keyProperty = "wikiId")
    @Insert("insert into wiki(title,article_id) values(#{title},#{articleId})")
    public int insertWiki(Wiki wiki);

    @Update("update wiki set title=#{title},article_id=#{articleId} where wiki_id=#{wikiId}")
    public int updateWiki(Wiki wiki);

    @Select("select * from wiki where title like #{title} limit #{start},#{quantity}")
    public List<Wiki> findByPageTitle(@Param("title") String title, @Param("start") int start, @Param("quantity") int quantity);

    @Select("select * from wiki where title like #{title}")
    public List<Wiki> findByTitle(String title);
}
