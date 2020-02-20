package com.gpnu.boshen.service.impl;

import com.gpnu.boshen.dto.FileInfo;
import com.gpnu.boshen.dto.NewsInfo;
import com.gpnu.boshen.entity.Article;
import com.gpnu.boshen.entity.News;
import com.gpnu.boshen.enums.NewsStateEnum;
import com.gpnu.boshen.mapper.ArticleMapper;
import com.gpnu.boshen.mapper.NewsMapper;
import com.gpnu.boshen.service.NewsService;
import com.gpnu.boshen.util.FileUploadUtil;
import com.gpnu.boshen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;


@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    ArticleMapper articleMapper;

    @Override
    @Transactional      //事务管理起来
    public ResultVo addNews(NewsInfo newsInfo) {
        File dest = new File("");  //外放是给catch用
        try {
            FileInfo fileInfo = FileUploadUtil.image(newsInfo.getAuthorId(),newsInfo.getImage());
            //测试
            System.out.println("文件上传了"+fileInfo);
            if (fileInfo == null){
                return new ResultVo(NewsStateEnum.FAIL_NOT_IMAGE);
            }
            dest = new File(fileInfo.getFrontPath()+fileInfo.getBackPath());

            //3.新闻的默认设置
            News news = new News();
            news.setIsSlider(false);
            news.setCategoryId(newsInfo.getCategoryId());
            news.setAuthorId(newsInfo.getAuthorId());
            news.setTitle(newsInfo.getTitle());
            news.setImageUrl(fileInfo.getBackPath());
            news.setCreateTime(new Date());
            //4.保存文章，返回articleId,set
            Article article = new Article();
            article.setContent(newsInfo.getArticle());
            articleMapper.addArticle(article);
            //测试
            System.out.println("文章添加后");
            news.setArticleId(article.getArticleId());
            //5.addNews
            int result = newsMapper.addNews(news);
            if(result <= 0){

            }
            //测试
            System.out.println("新闻添加后");
        }catch(Exception e){
            dest.delete(); //保持事务一致性
            throw new RuntimeException("新闻添加失败"+e.getMessage());
        }
        return new ResultVo(NewsStateEnum.SUCCESS);
    }

    @Override
    @Transactional
    public ResultVo deleteNews(int newsId) {
        //查询news
        News news = new News();
        news.setNewsId(newsId);
        news = newsMapper.findByNews(news).get(0);
        System.out.println(news);
        File image = new File(news.getImageUrl());

        try{    //因为外键关系，新闻最先删，又图片一旦删了就不能恢复，所以图片最后
            //删新闻
            int resultDN  = newsMapper.deleteNews(newsId);
            //删内容
            int resultDA = articleMapper.deleteArticle(news.getArticleId());
            //删图片
            image.delete();
        }catch(Exception e){
            throw new RuntimeException("删除新闻错误"+e.getMessage());
        }

        return new ResultVo(NewsStateEnum.SUCCESS);
    }


    @Override
    public ResultVo findByNews(News news) {
        if (news.getTitle()!= null){
            news.setTitle("%" + news.getTitle() + "%");
        }
        List<News> list =newsMapper.findByNews(news);
        ResultVo resultVo = new ResultVo(NewsStateEnum.SUCCESS);
        resultVo.setData(list);
        return resultVo;
    }

    @Override
    public ResultVo findByPage(String title, int pageStart, int quantity) {
        title = "%"+title+"%";  //模糊查询
        List<News> list = newsMapper.findByPage(title,pageStart,quantity);
        ResultVo resultVo = new ResultVo(NewsStateEnum.SUCCESS);
        resultVo.setData(list);
        return resultVo;
    }

    @Override
    public ResultVo updateIsSlider(News news){
        newsMapper.updateIsSlider(news);
        return new ResultVo(NewsStateEnum.SUCCESS);
    }
}
