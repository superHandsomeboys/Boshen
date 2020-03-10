package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 评论
 */
@Data
public class Comment {
    private Integer commentId;
    /**
     * 评论者id
     */
    private Integer authorId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论的新闻id
     */
    private Integer newsId;
    /**
     * 对公司的评论
     */
    private Integer companyId;
    /**
     * 对科技前沿的评论
     */
    private Integer scienceId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 评论的子评论
     */
    private Integer parentCommentId;
}
