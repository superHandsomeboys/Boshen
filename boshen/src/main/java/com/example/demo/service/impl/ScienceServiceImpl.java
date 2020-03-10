package com.example.demo.service.impl;

import com.example.demo.dto.FileInfo;
import com.example.demo.dto.ScienceDTO;
import com.example.demo.dto.ScienceRDTO;
import com.example.demo.dynamic.Data;
import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Science;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.ScienceMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.ScienceService;
import com.example.demo.util.FileUploadUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScienceServiceImpl implements ScienceService {
    @Autowired
    ScienceMapper scienceMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommentMapper commentMapper;


    @Override
    @Transactional
    public int addScience(ScienceRDTO scienceRDTO) {
        File dest = new File("");   //给catch用的
        try{
            Science science = new Science();
            //1.添加文章类
            Article article = new Article();
            article.setContent(scienceRDTO.getContent());
            articleMapper.addArticle(article);
            //一,设置文章id
            science.setArticleId(article.getArticleId());
            //2.上传图片
            FileInfo fileInfo = FileUploadUtil.image(scienceRDTO.getAuthorId(),scienceRDTO.getImage());
            if (fileInfo == null){
                return 0;
            }
            dest = new File(fileInfo.getFrontPath() + fileInfo.getBackPath());
            //3.添加剩余参数
            science.setImageUrl(fileInfo.getBackPath());
            BeanUtils.copyProperties(scienceRDTO,science);
            science.setCommentQuantity(0);  //0评论
            science.setCreateTime(new Date());
            return scienceMapper.insertScience(science);
        }catch (Exception e){
            dest.delete();
            throw new RuntimeException("添加科技文章错误！"+ e.getMessage());
        }

    }

    @Override
    public List<ScienceDTO> findPage(int page) {
        int start =(page-1) * Data.QUANTITY_IN_PAGE;
        return toDTOs(scienceMapper.findLimit(start,Data.QUANTITY_IN_PAGE));
    }

    @Override
    public int findPageQuantity() {
        int size = scienceMapper.getAllScience().size();
        if (size == 0){
            return 0;
        }else if (size % Data.QUANTITY_IN_PAGE == 0){   //刚好整页
            return size / Data.QUANTITY_IN_PAGE;
        }
        return size / Data.QUANTITY_IN_PAGE +1;
    }

    @Override
    public ScienceDTO findByScienceId(int scienceId) {
        return toDTO(scienceMapper.getScienceById(scienceId));
    }

    @Override
    public Science lastScience(int scienceId) {
        //获得最小的scienceid
        List<Science> sciences = scienceMapper.getAllScience();
        int minId = sciences.get(0).getScienceId();
        int lastId = scienceId-1;
        //如果id-1的science为空，并且当前的lastid>=minid,那就继续找
        if (scienceMapper.getScienceById(lastId) == null && lastId>=minId){
            lastId--;
        }
        if (lastId < minId){
            //说明没有上一个
            return null;
        }else {
            //有上一个
            return scienceMapper.getScienceById(lastId);
        }
    }

    @Override
    public Science nextScience(int scienceId) {
        //获得最大的scienceid
        List<Science> sciences = scienceMapper.getAllScience();
        int maxId = sciences.get(sciences.size()-1).getScienceId();
        int nestId = scienceId + 1;
        //如果id+1的science为空，并且当前的nestid<=minid,那就继续找
        if (scienceMapper.getScienceById(nestId) == null && nestId<=maxId){
            nestId++;
        }
        if (nestId > maxId){
            //说明没有下一个
            return null;
        }else {
            //有下一个
            return scienceMapper.getScienceById(nestId);
        }
    }

    @Override
    public List<ScienceDTO> findLimit(int start, int quantity) {
        return toDTOs(scienceMapper.findLimit(start,quantity));
    }

    @Override
    public List<Science> findByPageTitle(String title, int page) {
        String t = "%" + title + "%";
        int start =(page-1) * Data.QUANTITY_IN_PAGE;
        return scienceMapper.findByPageTitle(t,start,Data.QUANTITY_IN_PAGE);
    }

    @Override
    public int findPageQuantityByTitle(String title) {
        String t = "%" + title + "%";
        int size = scienceMapper.findByTitle(t).size();
        if (size == 0){
            return 0;
        }else if (size % Data.QUANTITY_IN_PAGE == 0){   //刚好整页
            return size / Data.QUANTITY_IN_PAGE;
        }
        return size / Data.QUANTITY_IN_PAGE +1;
    }

    @Override
    @Transactional
    public void deleteScience(int id) {
        //查询news
        Science science = scienceMapper.getScienceById(id);
        File image = new File(Data.UPLOAD_IMAGE_PATH + science.getImageUrl());

        try{    //因为外键关系，新闻最先删，又图片一旦删了就不能恢复，所以图片最后
            //删除评论
            Comment comment = new Comment();
            comment.setScienceId(id);
            //所有父评论
            for (Comment c : commentMapper.findById(comment)){
                //删除子评论
                Comment c2 = new Comment();
                c2.setParentCommentId(c.getCommentId());
                for (Comment c3 : commentMapper.findById(c2)){
                    commentMapper.deletComment(c3.getCommentId());
                }
                commentMapper.deletComment(c.getCommentId());
            }
            //删新闻
            int resultDN  = scienceMapper.deleteScience(id);
            //删内容
            int resultDA = articleMapper.deleteArticle(science.getArticleId());
            //删图片
            image.delete();
            return ;
        }catch(Exception e){
            throw new RuntimeException("删除新闻错误"+e.getMessage());
        }
    }

    @Override
    public List<Science> findByAuthorId(int authorId) {
        return scienceMapper.findByAuthorId(authorId);
    }

    /**
     * 工具方法 -》DTOs
     */
    public List<ScienceDTO> toDTOs(List<Science> sciences){
        List<ScienceDTO> scienceDTOS = new ArrayList<ScienceDTO>();
        for (Science science : sciences){
            ScienceDTO scienceDTO = new ScienceDTO();
            BeanUtils.copyProperties(science,scienceDTO);
            scienceDTO.setArticle(articleMapper.findArticleById(science.getArticleId()));
            scienceDTO.setAuthor(userMapper.findByUserId(science.getAuthorId()));
            scienceDTOS.add(scienceDTO);
        }
        return scienceDTOS;
    }

    public ScienceDTO toDTO(Science science){
        ScienceDTO scienceDTO = new ScienceDTO();
        BeanUtils.copyProperties(science,scienceDTO);
        scienceDTO.setArticle(articleMapper.findArticleById(science.getArticleId()));
        scienceDTO.setAuthor(userMapper.findByUserId(science.getAuthorId()));
        return scienceDTO;
    }
}
