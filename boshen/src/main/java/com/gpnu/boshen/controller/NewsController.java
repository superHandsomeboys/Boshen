package com.gpnu.boshen.controller;

import com.gpnu.boshen.dto.NewsInfo;
import com.gpnu.boshen.dynamic.Data;
import com.gpnu.boshen.entity.News;
import com.gpnu.boshen.enums.NewsStateEnum;
import com.gpnu.boshen.service.NewsService;
import com.gpnu.boshen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class NewsController {

    @Autowired
    NewsService newsService;
//        测试addNews，通过file.html
//    @PostMapping("/addNews")
//    public ResultVo addNews(MultipartFile file){
//        NewsInfo newsInfo = new NewsInfo();
//        newsInfo.setArticle("没没没没没");
//        newsInfo.setAuthorId(2);
//        newsInfo.setCategoryId(6);
//        newsInfo.setTitle("newt");
//        newsInfo.setImage(file);
//        return newsService.addNews(newsInfo);
//    }

    /**
     * 添加新闻
     * @param newsInfo
     * @return
     */
    @PostMapping("/news")
    public ResultVo addNews(NewsInfo newsInfo){
        if (newsInfo.getImage() == null || newsInfo.getAuthorId() == null || newsInfo.getArticle() == null
                || newsInfo.getCategoryId() == null || newsInfo.getTitle()==null){
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        return newsService.addNews(newsInfo);
    }

    /**
     * 删除新闻
     * @param newsId
     * @return
     */
    @DeleteMapping("/news/{id}")
    public ResultVo deleteNews(@PathVariable("id") Integer newsId){
        if (newsId == null){
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        return newsService.deleteNews(newsId);
    }

    /**
     * 根据newsid查询news
     * @param newsId
     * @return
     */
    @GetMapping("/news/newsId/{id}")
    public ResultVo findNewsByNewsId(@PathVariable("id") Integer newsId){
        if (newsId == null){
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        News news = new News();
        news.setNewsId(newsId);
        return newsService.findByNews(news);
    }

    /**
     * 根据title模糊查询news（不分页）
     * @param title
     * @return
     */
    @GetMapping("/news/title/{title}")
    public ResultVo findNewsByTitle(@PathVariable("title") String title){
        if (title == null){
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        News news = new News();
        news.setTitle(title);
        return newsService.findByNews(news);
    }

    /**
     * 根据authorid查询news
     * @param authorId
     * @return
     */
    @GetMapping("/news/authorId/{id}")
    public ResultVo findNewsByAuthor(@PathVariable("id") Integer authorId){
        if (authorId == null){
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        News news = new News();
        news.setAuthorId(authorId);
        return newsService.findByNews(news);
    }

    /**
     * 根据categoryId查询news
     * @param categoryId
     * @return
     */
    @GetMapping("/news/categoryId/{id}")
    public ResultVo findNewsByCategory(@PathVariable("id") Integer categoryId){
        if (categoryId == null){
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        News news = new News();
        news.setCategoryId(categoryId);
        return newsService.findByNews(news);
    }

    /**
     * 查询新闻首页轮播图的news
     * @param isSlider
     * @return
     */
    @GetMapping("/news/sliderId/{isSlider}")
    public ResultVo findNewsBySlider(@PathVariable("isSlider") Boolean isSlider){
        if (isSlider == null){
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        News news = new News();
        news.setIsSlider(isSlider);
        return newsService.findByNews(news);
    }

    /**
     * 有条件的分页查询
     * @param title
     * @param page
     * @return
     */
    @GetMapping("/news/title/{title}/page/{page}")
    public ResultVo findByPage(@PathVariable("title") String title,@PathVariable("page") Integer page){
        if (title == null || page == null){
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        int quantity = page + Data.PAGE;
        return newsService.findByPage(title,page,quantity);
    }

    /**
     * 设置新闻为轮播图
     * @param news
     * @return
     */
    @PutMapping("/news/isSlider")
    public ResultVo updateIsSlider(@RequestBody News news){
        if (news.getNewsId() == null || news.getIsSlider() == null){
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        return newsService.updateIsSlider(news);
    }
}
