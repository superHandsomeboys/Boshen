package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.Comment;
import com.gpnu.boshen.mapper.dynamicSql.CommentDS;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 添加评论语句，通过动态sql语句统一化，自动分辨是新闻，公司，还是子的评论
     * @param comment
     * @return
     */
    @InsertProvider(type = CommentDS.class,method = "insert")
    public int addComment(Comment comment);

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @Delete("delete from comment where comment_id =#{commentId}")
    public int deletComment(int commentId);

    /**
     * 根据id查询，包括新闻id，用户id等，查询所有有关的评论，动态sql
     * @param comment
     * @return
     */
    @SelectProvider(type = CommentDS.class,method = "select")
    public List<Comment> findById(Comment comment);

    @Select("select * from comment")
    public List<Comment> findAll();
}
