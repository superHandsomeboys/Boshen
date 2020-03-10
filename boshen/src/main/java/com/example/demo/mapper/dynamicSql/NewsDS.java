package com.example.demo.mapper.dynamicSql;

import com.example.demo.entity.News;
import org.apache.ibatis.jdbc.SQL;

public class NewsDS {
    public String select(News news){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM("news");
                if (news.getNewsId()!=null){    //根据新闻id查询
                    WHERE("news_id = #{newsId}");
                }
                if (news.getTitle()!=null){     //根据关键词，套标题模糊查询,title=%xxx%
                    WHERE("title like #{title}");
                }
                if (news.getAuthorId()!=null){      //根据作者查询所有发布的新闻
                    WHERE("author_id = #{authorId}");
                }
                if (news.getCategoryId()!=null){    //查询该类型下的所有新闻
                    WHERE("category_id = #{categoryId}");
                }
                if (news.getIsSlider() !=null){      //查询轮播图的新闻,不能==true,会行为isSlider为空报错
                    WHERE("is_slider = #{isSlider}");
                }   //都跳过，则查询所有

            }
        }.toString();
        return sql;
    }

    public String delete(int newsId){
        return new SQL(){
            {
                DELETE_FROM("news");
                WHERE("news_id = #{newsId}");
            }
        }.toString();
    }
}
