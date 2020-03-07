package com.gpnu.boshen.mapper.dynamicSql;

import com.gpnu.boshen.entity.Comment;
import org.apache.ibatis.jdbc.SQL;

/**
 * comment类的动态sql,依赖于mybatis包
 */
public class CommentDS {
    public String insert(Comment comment){
        return new SQL(){
            {
                INSERT_INTO("comment");
                VALUES("content","#{content}");
                VALUES("create_time","#{createTime}");
                VALUES("author_id","#{authorId}");
                if (comment.getCompanyId() !=null){     //是对公司的评论
                    VALUES("company_id","#{companyId}");
                }
                if (comment.getNewsId()!=null){     //是对新闻的评论
                    VALUES("news_id","#{newsId}");
                }
                if (comment.getParentCommentId()!=null) {   //是评论的子评论
                    VALUES("parent_comment_id","#{parentCommentId}");
                }
                if (comment.getScienceId()!=null){  //科学文章
                    VALUES("science_id","#{scienceId}");
                }
            }
        }.toString();
    }

    public String select(Comment comment){
        return new SQL(){
            {
                SELECT("*");
                FROM("comment");
                if (comment.getParentCommentId()!=null){    //查询父评论的所有子评论
                    WHERE("parent_comment_id = #{parentCommentId}");
                }
                if (comment.getNewsId() != null){      //查询新闻的所有评论
                    WHERE("news_id = #{newsId}");
                }
                if (comment.getCompanyId() != null){    //查询公司的评价
                    WHERE("company_id = #{companyId}");
                }
                if (comment.getAuthorId() != null){     //查此人的所有评论
                    WHERE("author_id = #{authorId}");
                }
                if (comment.getScienceId() != null){    //查此科技前沿的所有评论
                    WHERE("science_id = #{scienceId}");
                }
                if (comment.getCommentId() != null){
                    WHERE("comment_id = #{commentId}");
                }
            }
        }.toString();
    }

}
