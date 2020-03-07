package com.example.demo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 视图中显示的评论数据类
 * 评论最多2层，不能无线嵌套
 */
@Data
public class CommentVO {
    private Integer commentId;

    //只用名字和头像
    private SimpleUserVO author;

    //评论时间
    private Date createTime;

    //评论内容
    private String content;

    //子评论
    private List<CommentVO> sonCommentList;
}
