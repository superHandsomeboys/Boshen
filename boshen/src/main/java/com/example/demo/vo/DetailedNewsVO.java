package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 新闻详情页的数据
 */
@Data
public class DetailedNewsVO {
    //新闻标题
    private String title;

    //新闻附带的图片()
    private String imageUrl;

    //新闻内容()
    private String article;

    //新闻类别
    private String category;

    //评论数量
    private Integer commentQuantity;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    //上一篇文章(只用newsId,title)
    private SimpleNewsVO lastNews;

    //下一篇文章(只用newsId,title)
    private SimpleNewsVO nextNews;

    //新闻作者类
    private SimpleUserVO author;

    //相关新闻
    private List<SimpleNewsVO> relatedNewsList;

    //评论
    private List<CommentVO> commentList;
}
