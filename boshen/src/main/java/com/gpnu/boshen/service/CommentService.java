package com.gpnu.boshen.service;


import com.gpnu.boshen.entity.Comment;
import com.gpnu.boshen.vo.ResultVo;

public interface CommentService {
    /**
     * 添加评论你
     * @param comment
     * @return
     */
    public ResultVo addComment(Comment comment);

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    public ResultVo deleteComment(int commentId);

    /**
     * 根据各个id查找评论
     * @param comment
     * @return
     */
    public ResultVo findById(Comment comment);
}
