package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.Comment;
import com.gpnu.boshen.enums.CommentStateEnum;
import com.gpnu.boshen.service.CommentService;
import com.gpnu.boshen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 添加评论
     * @return
     */
    @PostMapping("/comment")
    public ResultVo addComment(@RequestBody Comment comment){
        if (comment.getAuthorId() == null || comment.getContent() == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        return commentService.addComment(comment);
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @DeleteMapping("/comment/{id}")
    public ResultVo deleteComment(@PathVariable("id") Integer commentId){
        if (commentId == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        return commentService.deleteComment(commentId);
    }

    /**
     * 根据评论id查询评论
     * @param commentId
     * @return
     */
    @GetMapping("/comment/commentId/{id}")
    public ResultVo findCommentByComment(@PathVariable("id")Integer commentId){
        if (commentId == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        Comment comment = new Comment();
        comment.setCommentId(commentId);
        return commentService.findById(comment);
    }

    /**
     * 根据父评论id查询评论
     * @param parentCommentId
     * @return
     */
    @GetMapping("/comment/parentCommentId/{id}")
    public ResultVo findCommentByParentComment(@PathVariable("id")Integer parentCommentId){
        if (parentCommentId == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        Comment comment = new Comment();
        comment.setParentCommentId(parentCommentId);
        return commentService.findById(comment);
    }

    /**
     * 根据新闻id查询评论
     * @param newsId
     * @return
     */
    @GetMapping("/comment/newsId/{id}")
    public ResultVo findCommentByNews(@PathVariable("id")Integer newsId){
        if (newsId == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        Comment comment = new Comment();
        comment.setNewsId(newsId);
        return commentService.findById(comment);
    }

    /**
     * 根据公司id查询评论
     * @param companyId
     * @return
     */
    @GetMapping("/comment/companyId/{id}")
    public ResultVo findCommentByCompany(@PathVariable("id")Integer companyId){
        if (companyId ==null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        Comment comment = new Comment();
        comment.setCompanyId(companyId);
        return commentService.findById(comment);
    }

    /**
     * 根据人id查询评论
     * @param authorId
     * @return
     */
    @GetMapping("/comment/authorId/{id}")
    public ResultVo findCommentByAuthor(@PathVariable("id")Integer authorId){
        if (authorId == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        Comment comment = new Comment();
        comment.setAuthorId(authorId);
        return commentService.findById(comment);
    }

    /**
     * 根据科技前沿的scienceid查询评论
     * @param scienceId
     * @return
     */
    @GetMapping("/comment/scienceId/{id}")
    public ResultVo findCommentByScience(@PathVariable("id")Integer scienceId){
        if (scienceId == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        Comment comment = new Comment();
        comment.setScienceId(scienceId);
        return commentService.findById(comment);
    }

}
