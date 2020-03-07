package com.gpnu.boshen.service;


import com.gpnu.boshen.dto.CommentDTO;
import com.gpnu.boshen.entity.Comment;
import com.gpnu.boshen.vo.ResultVo;

import java.util.List;

public interface CommentService {
    /**
     * 添加评论你
     * @param comment
     * @return
     */
    public ResultVo addComment(Comment comment);

    /**
     * 删除子评论
     * @param commentId
     * @return
     */
    public ResultVo deleteSonComment(int commentId);

    /**
     * 删除父评论
     */
    public ResultVo deleteParentComment(int commentId);


    /**
     * 根据各个id查找评论
     * @param comment
     * @return
     */
    public ResultVo findById(Comment comment);

    /**
     * 用comment的newsid，companyId等id查询所有的评论
     * @param comment
     * @return
     */
    public List<CommentDTO> findComment(Comment comment);

    /**
     * 查询所有
     */
    public List<Comment> findAll();

}
