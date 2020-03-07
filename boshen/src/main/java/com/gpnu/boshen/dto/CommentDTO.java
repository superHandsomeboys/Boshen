package com.gpnu.boshen.dto;

import com.gpnu.boshen.entity.Comment;
import com.gpnu.boshen.entity.News;
import com.gpnu.boshen.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentDTO {

    private Integer commentId;

    private String avatar;

    private String author;
    /**
     * 评论内容
     */
    private String content;

    /**
     * 子评论
     */
    private List<CommentDTO> sonCommentDTO;

    private Date createTime;
}
