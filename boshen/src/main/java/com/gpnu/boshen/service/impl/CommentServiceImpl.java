package com.gpnu.boshen.service.impl;

import com.gpnu.boshen.entity.Comment;
import com.gpnu.boshen.enums.CommentStateEnum;
import com.gpnu.boshen.mapper.CommentMapper;
import com.gpnu.boshen.service.CommentService;
import com.gpnu.boshen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper commentMapper;

    @Override
    public ResultVo addComment(Comment comment) {
        //添加
        comment.setCreateTime(new Date());
        commentMapper.addComment(comment);
        return new ResultVo(CommentStateEnum.SUCCESS);
    }

    @Override
    public ResultVo deleteComment(int commentId) {
        int result = commentMapper.deletComment(commentId);
        if (result <= 0){
            //评论为空
            return new ResultVo(CommentStateEnum.FAIL_NULL);
        }
        return new ResultVo(CommentStateEnum.SUCCESS);
    }

    @Override
    public ResultVo findById(Comment comment) {
        List<Comment> list = commentMapper.findById(comment);
        ResultVo resultVo = new ResultVo(CommentStateEnum.SUCCESS);
        resultVo.setData(list);
        return resultVo;
    }
}
