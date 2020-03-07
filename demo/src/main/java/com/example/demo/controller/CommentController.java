package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.enums.CommentStateEnum;
import com.example.demo.service.CommentService;
import com.example.demo.vo.ResultVo;
import com.example.demo.vo.SimpleCommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes(value = {"userId"})
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 添加新闻评论
     * @return
     */
    @PostMapping("/comment/news")
    public ResultVo addNewsComment(@RequestBody Comment comment, Model model){
        if (comment.getNewsId() == null || comment.getContent() == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        int userId = (Integer)model.getAttribute("userId");
        comment.setAuthorId(userId);
        return commentService.addComment(comment);
    }

    /**
     * 添加科技评论
     * @return
     */
    @PostMapping("/comment/science")
    public ResultVo addScienceComment(@RequestBody Comment comment, Model model){
        if (comment.getContent() == null || comment.getScienceId() == null ){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        int userId = (Integer)model.getAttribute("userId");
        comment.setAuthorId(userId);
        return commentService.addComment(comment);
    }

    /**
     * 添加公司的评论
     */
    @PostMapping("/comment/company")
    public ResultVo addCompanyComment(@RequestBody Comment comment, Model model){
        if (comment.getContent() == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        int userId = (Integer)model.getAttribute("userId");
        comment.setAuthorId(userId);
        comment.setCompanyId(1);
        return commentService.addComment(comment);
    }

    /**
     * 添加子的评论
     */
    @PostMapping("/comment/son")
    public ResultVo addSonComment(@RequestBody Comment comment, Model model){
        if (comment.getContent() == null || comment.getParentCommentId() == null ){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        int userId = (Integer)model.getAttribute("userId");
        comment.setAuthorId(userId);
        return commentService.addComment(comment);
    }


    /**
     * 删除子评论
     * @param commentId
     * @return
     */
    @DeleteMapping("/comment/son/{id}")
    public ResultVo deleteSonComment(@PathVariable("id") Integer commentId){
        if (commentId == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        return commentService.deleteSonComment(commentId);
    }

    /**
     * 删除父评论,同时删除父评论下的子评论
     */
    @DeleteMapping("/comment/parent/{id}")
    public ResultVo deleteParentComment(@PathVariable("id") Integer commentId){
        if (commentId == null){
            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
        }
        return commentService.deleteParentComment(commentId);
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


    //5.查5个用户对公司的评价
    @GetMapping("/comment/company")
    public ResultVo findCompanyComment(){
        Comment comment = new Comment();
        comment.setCompanyId(1);
        List<CommentDTO> commentDTOS = commentService.findComment(comment);
        List<SimpleCommentVO> simpleCommentVOS = new ArrayList<SimpleCommentVO>();
        for (CommentDTO commentDTO : commentDTOS){
            SimpleCommentVO simpleCommentVO = new SimpleCommentVO();
            BeanUtils.copyProperties(commentDTO,simpleCommentVO);
            simpleCommentVOS.add(simpleCommentVO);
        }
        return new ResultVo(CommentStateEnum.SUCCESS,simpleCommentVOS);

    }

    /**
     * 查询科技文章的所有评论
     */
    @GetMapping("/comment/scienceId/{id}")
    public ResultVo findScienceComments(@PathVariable("id") Integer scienceId){
        Comment comment = new Comment();
        comment.setScienceId(scienceId);
        return new ResultVo(CommentStateEnum.SUCCESS,commentService.findComment(comment));
    }

    /**
     * 查所有评论
     */
    @GetMapping("/comment/list")
    public ResultVo findScienceComments(){
        return new ResultVo(CommentStateEnum.SUCCESS,commentService.findAll());

    }




}
