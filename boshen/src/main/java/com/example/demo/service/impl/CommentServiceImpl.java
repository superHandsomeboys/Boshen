package com.example.demo.service.impl;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.*;
import com.example.demo.enums.CommentStateEnum;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.mapper.ScienceMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.CommentService;
import com.example.demo.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ScienceMapper scienceMapper;
    @Autowired
    NewsMapper newsMapper;

    @Override
    @Transactional
    public ResultVo addComment(Comment comment) {
        try{
            //1.在相应的文章下增加评论数量
            if (comment.getScienceId() != null){
                Science science = scienceMapper.getScienceById(comment.getScienceId());
                science.setCommentQuantity(science.getCommentQuantity()+1);
                scienceMapper.updateScience(science);
            }
            if (comment.getNewsId() != null){
                News news1 = new News();
                news1.setNewsId(comment.getNewsId());
                News news = newsMapper.findByNews(news1).get(0);
                news.setCommentQuantity(news.getCommentQuantity()+1);
                newsMapper.updateNews(news);
            }
            //2.添加评论
            comment.setCreateTime(new Date());
            commentMapper.addComment(comment);
            return new ResultVo(CommentStateEnum.SUCCESS);
        }catch (Exception e){
            throw new RuntimeException("添加评论失败"+ e.getMessage());
        }
    }

    @Override
    public ResultVo deleteSonComment(int commentId) {
        int result = commentMapper.deletComment(commentId);
        if (result <= 0){
            //评论为空
            return new ResultVo(CommentStateEnum.FAIL_NULL);
        }
        return new ResultVo(CommentStateEnum.SUCCESS);
    }

    @Transactional
    @Override
    public ResultVo deleteParentComment(int commentId) {
        try{
            //1.删除只子评论
            Comment comment = new Comment();
            comment.setParentCommentId(commentId);
            List<Comment> comments = commentMapper.findById(comment);

            System.out.println(comments.size());
            for (Comment comment1 : comments){
                commentMapper.deletComment(comment1.getCommentId());
            }
            System.out.println("子评论已删除");
            //2.更新评论数量
            Comment comment1 = new Comment();
            comment1.setCommentId(commentId);
            Comment comment2 = commentMapper.findById(comment1).get(0);

            System.out.println(comment2);
            //2.1如果是新闻评论，
            if (comment2.getNewsId() !=null){
                News news = new News();
                news.setNewsId(comment2.getNewsId());
                News news1 = newsMapper.findByNews(news).get(0);
                news1.setCommentQuantity(news1.getCommentQuantity()-1);
                newsMapper.updateNews(news1);
            }
            //2.2如果是科技评论，更新评论数量
            if (comment2.getScienceId() != null){
                Science science = scienceMapper.getScienceById(comment2.getScienceId());
                science.setCommentQuantity(science.getCommentQuantity()-1);
                scienceMapper.updateScience(science);
            }
            System.out.println("评论数量已更新");
            //3.删除评论
            commentMapper.deletComment(commentId);
            return new ResultVo(CommentStateEnum.SUCCESS);
        }catch (Exception e){
            throw new RuntimeException("删除父评论失败："+ e.getMessage());
        }

    }

    @Override
    public ResultVo findById(Comment comment) {
        List<Comment> list = commentMapper.findById(comment);
        ResultVo resultVo = new ResultVo(CommentStateEnum.SUCCESS);
        resultVo.setData(list);
        return resultVo;
    }

    @Override
    public List<CommentDTO> findComment(Comment comment) {
        return toDTOs(commentMapper.findById(comment));
    }

    @Override
    public List<Comment> findAll() {
        return commentMapper.findAll();
    }

    /**
     * 工具类把List<comment> -> List<commentDTO>
     */
    public List<CommentDTO> toDTOs(List<Comment> comments){
        List<CommentDTO> pcommentDTOS = new ArrayList<CommentDTO>();
        for (Comment pcomment : comments){
            CommentDTO pcommentDTO = new CommentDTO();
            BeanUtils.copyProperties(pcomment,pcommentDTO);
            User author = userMapper.findByUserId(pcomment.getAuthorId());
            pcommentDTO.setAvatar(author.getAvatar());
            pcommentDTO.setAuthor(author.getUserName());
            //子评论,最多只有两层评论
            Comment comment1 = new Comment();
            comment1.setParentCommentId(pcomment.getCommentId());
            List<Comment> scommentList = commentMapper.findById(comment1);
            List<CommentDTO> scommentDTOList = new ArrayList<CommentDTO>();
            for (Comment scomment : scommentList){
                CommentDTO scommentDTO = new CommentDTO();
                BeanUtils.copyProperties(scomment,scommentDTO);
                User sonAuthor = userMapper.findByUserId(scomment.getAuthorId());
                scommentDTO.setAvatar(sonAuthor.getAvatar());
                scommentDTO.setAuthor(sonAuthor.getUserName());
                scommentDTOList.add(scommentDTO);
            }
            //添加子评论
            pcommentDTO.setSonCommentDTO(scommentDTOList);
            //添加父评论类
            pcommentDTOS.add(pcommentDTO);
        }
        return pcommentDTOS;
    }
    /**
     * 工具类把comment -> commentDTO
     */
    public CommentDTO toDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(comment.getContent());
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setCreateTime(comment.getCreateTime());
        User author = userMapper.findByUserId(comment.getAuthorId());
        commentDTO.setAvatar(author.getAvatar());
        commentDTO.setAuthor(author.getUserName());
        return commentDTO;
    }
}
