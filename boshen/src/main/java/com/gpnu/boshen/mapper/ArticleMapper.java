package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.Article;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ArticleMapper {

    @Select("select * from article where article_id = #{id}")
    public Article getArticleById(int id);

    @Options(useGeneratedKeys = true, keyProperty = "article_id")
    @Insert("insert into article(content) values (#{content})")
    public Article insertArticle(Article article);

    @Update("update article set content = #{content} where article_id = #{article_id}")
    public Article update(Article article);

    @Delete("delete from article where article_id = #{id}")
    public Article delete(int id);

}
