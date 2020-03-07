package com.example.demo.mapper;

import com.example.demo.entity.Article;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ArticleMapper {
    /**
     * 增加文章
     * @param article
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "articleId")
    @Insert("insert into article (content) values(#{content})")
    public int addArticle(Article article);

    /**
     * 根据articleid删除文章
     * @param articleId
     * @return
     */
    @Delete("delete from article where article_id=#{articleId}")
    public int deleteArticle(int articleId);

    /**
     * 更新文章
     * @param article
     * @return
     */
    @Update("update article set content=#{content} where article_id=#{articleId}")
    public int updateArticle(Article article);

    /**
     * 用id查文章
     * @param articleId
     * @return
     */
    @Select("select * from article where article_id=#{articleId}")
    public Article findArticleById(int articleId);
}
