package com.gpnu.boshen.controller;

import com.gpnu.boshen.dto.NewsDTO;
import com.gpnu.boshen.dto.NewsIndexDTO;
import com.gpnu.boshen.dto.NewsInfo;
import com.gpnu.boshen.dynamic.Data;
import com.gpnu.boshen.entity.News;
import com.gpnu.boshen.entity.NewsCategory;
import com.gpnu.boshen.enums.NewsStateEnum;
import com.gpnu.boshen.service.NewsCategoryService;
import com.gpnu.boshen.service.NewsService;
import com.gpnu.boshen.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NewsController {

    @Autowired
    NewsService newsService;
    @Autowired
    NewsCategoryService newsCategoryService;


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
     *
     */
    @PostMapping("/news")
    public ResultVo addNews(NewsInfo newsInfo) {
        if (newsInfo.getImage() == null || newsInfo.getAuthorId() == null || newsInfo.getArticle() == null
                || newsInfo.getCategoryId() == null || newsInfo.getTitle() == null) {
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        return newsService.addNews(newsInfo);
    }

    /**
     * 删除新闻
     *
     */
    @DeleteMapping("/news/{id}")
    public ResultVo deleteNews(@PathVariable("id") Integer newsId) {
        if (newsId == null) {
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        return newsService.deleteNews(newsId);
    }

    /**
     * 根据newsid查询新闻详情页的所有数据
     *
     */
    @GetMapping("/news/newsId/{id}")
    public ResultVo findDetailedNewsVOByNewsId(@PathVariable("id") Integer newsId) {
        if (newsId == null) {
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        return newsService.findDetailedNewsVOByNewsId(newsId);
    }

    /**
     * 根据title模糊查询news（不分页）
     *
     */
    @GetMapping("/news/title/{title}")
    public ResultVo findNewsByTitle(@PathVariable("title") String title) {
        if (title == null) {
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        News news = new News();
        news.setTitle(title);
        return new ResultVo(NewsStateEnum.SUCCESS, newsService.findByNews(news));
    }

    /**
     * 根据authorid查询news
     *
     */
    @GetMapping("/news/authorId/{id}")
    public ResultVo findNewsByAuthor(@PathVariable("id") Integer authorId) {
        if (authorId == null) {
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        News news = new News();
        news.setAuthorId(authorId);
        return new ResultVo(NewsStateEnum.SUCCESS, newsService.findByNews(news));
    }

    /**
     * 根据categoryId查询news
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/news/categoryId/{id}")
    public ResultVo findNewsByCategory(@PathVariable("id") Integer categoryId) {
        if (categoryId == null) {
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        News news = new News();
        news.setCategoryId(categoryId);
        return new ResultVo(NewsStateEnum.SUCCESS, newsService.findByNews(news));
    }

    /**
     * 查询新闻首页轮播图的news
     *
     * @param isSlider
     * @return
     */
    @GetMapping("/news/sliderId/{isSlider}")
    public ResultVo findNewsBySlider(@PathVariable("isSlider") Boolean isSlider) {
        if (isSlider == null) {
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        News news = new News();
        news.setIsSlider(isSlider);
        return new ResultVo(NewsStateEnum.SUCCESS, newsService.findByNews(news));
    }

    /**
     * 有条件的分页查询
     *
     * @param title
     * @param page
     * @return
     */
    @GetMapping("/news/title/{title}/page/{page}")
    public ResultVo findByPage(@PathVariable("title") String title, @PathVariable("page") Integer page) {
        if (title == null || page == null) {
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        int quantity = page + Data.PAGE;
        return newsService.findByPage(title, page, quantity);
    }

    /**
     * 设置新闻为轮播图
     *
     * @param news
     * @return
     */
    @PutMapping("/news/isSlider")
    public ResultVo updateIsSlider(@RequestBody News news) {
        if (news.getNewsId() == null || news.getIsSlider() == null) {
            return new ResultVo(NewsStateEnum.FAIL_NULL_PARAM);
        }
        return newsService.updateIsSlider(news);
    }

    /**
     * 查询新闻资讯页的全部资源
     */
    @GetMapping("/news/information")
    public ResultVo findNewsInformation() {
        //创建vo类
        NewsInfornationVO n = new NewsInfornationVO();
        //1.查询5个新闻（id,title）
        List<SimpleNewsVO> simpleNewsVOList = newsService.findLimit(5);
        n.setDynamicNewsList(simpleNewsVOList);
        //2.查6个轮播图新闻（id,imageUrl,title）
        List<SimpleNewsVO> simpleNewsVOList1 = newsService.findByIsSlider();
        n.setSliderNewsList(simpleNewsVOList1);
        //3.查2个新闻类别，以及各自的3个新闻（simpleNewsVO）
        List<NewsCategory> newsCategoryList = newsCategoryService.findLimit(2);
        int i = 1;
        for (NewsCategory newsCategory : newsCategoryList) {
            List<SimpleNewsVO> simpleNewsVOList2 = newsService.findByCategoryId(newsCategory.getNewsCategoryId(), 3);
            if (i == 1) {
                n.setFirstCategory(newsCategory.getNewsCategoryName());
                n.setFiestNewsList(simpleNewsVOList2);
            } else {
                n.setSecondCategory(newsCategory.getNewsCategoryName());
                n.setSecondNewsList(simpleNewsVOList2);
            }
            i++;
        }
        //4.查8个新闻（simpleNewsVO）
        List<SimpleNewsVO> simpleNewsVOList2 = newsService.findLimit(8);
        n.setOtherNewsList(simpleNewsVOList2);
        //5.查所有类别
        n.setCategoryList(newsCategoryService.findAllNewsCategory());
        //6.查评论最多的3个
        n.setHotNewsList(newsService.findHotest(3));
        //7.查最新发表的3个
        n.setNewNewsList(newsService.findNewest(3));
        return new ResultVo(NewsStateEnum.SUCCESS, n);
    }





    //2.查6个新闻（newsIndexVO,category,）
    @GetMapping("/news/indexData01")
    public ResultVo findNewsToIndex01(){
        List<NewsDTO> newsDTOList = newsService.findNewsDTOLimit(0,6);
        List<NewsIndexVO> newsIndexVOList = new ArrayList<NewsIndexVO>();
        for (NewsDTO newsDTO : newsDTOList){
            NewsIndexVO newsIndexVO = new NewsIndexVO();
            //1.放category
            Map<String,String> map = new HashMap<>();
            map.put("category",newsDTO.getCategory().getNewsCategoryName());
            newsIndexVO.setData(map);
            //2.封装非Object的数据
            newsIndexVO.setTitle(newsDTO.getTitle());
            newsIndexVO.setCreateTime(newsDTO.getCreateTime());
            newsIndexVO.setImageUrl(newsDTO.getImageUrl());
            newsIndexVO.setNewsId(newsDTO.getNewsId());
            //3.加入list
            newsIndexVOList.add(newsIndexVO);
        }
        //再一次封装，经过NewsDTO,NewsIndexVO,ResultVo，3次封装
        return new ResultVo(NewsStateEnum.SUCCESS,newsIndexVOList);
    }

    //4.1查前2个新闻（newsIndexVO,newsIndexDTO)，
    @GetMapping("/news/indexData02")
    public ResultVo findNewsToIndex02(){
        List<NewsDTO> newsDTOList = newsService.findNewsDTOLimit(0,2);
        List<NewsIndexVO> newsIndexVOList = new ArrayList<NewsIndexVO>();
        for (NewsDTO newsDTO : newsDTOList){
            NewsIndexVO newsIndexVO = new NewsIndexVO();
            //1.封装newsIndexDTO进data
            NewsIndexDTO newsIndexDTO = new NewsIndexDTO();
            newsIndexDTO.setAuthor(newsDTO.getAuthor().getAvatar());
            newsIndexDTO.setCategory(newsDTO.getCategory().getNewsCategoryName());
            newsIndexDTO.setIntroduce(newsDTO.getIntroduce());
            newsIndexVO.setData(newsIndexDTO);
            //2.封装非object数据
            newsIndexVO.setTitle(newsDTO.getTitle());
            newsIndexVO.setCreateTime(newsDTO.getCreateTime());
            newsIndexVO.setImageUrl(newsDTO.getImageUrl());
            newsIndexVO.setNewsId(newsDTO.getNewsId());
            //3.加入list
            newsIndexVOList.add(newsIndexVO);
        }
        return new ResultVo(NewsStateEnum.SUCCESS,newsIndexVOList);
    }

    //4.2查第二个开始后4个新闻（newsIndexVO,author）
    @GetMapping("/news/indexData03")
    public ResultVo findNewToIndex03(){
        List<NewsDTO> newsDTOList = newsService.findNewsDTOLimit(2,4);
        List<NewsIndexVO> newsIndexVOList = new ArrayList<NewsIndexVO>();
        for (NewsDTO newsDTO : newsDTOList){
            NewsIndexVO newsIndexVO = new NewsIndexVO();
            //1.放category
            Map<String,String> map = new HashMap<>();
            map.put("author",newsDTO.getAuthor().getAvatar());
            newsIndexVO.setData(map);
            //2.封装非Object的数据
            newsIndexVO.setTitle(newsDTO.getTitle());
            newsIndexVO.setCreateTime(newsDTO.getCreateTime());
            newsIndexVO.setImageUrl(newsDTO.getImageUrl());
            newsIndexVO.setNewsId(newsDTO.getNewsId());
            //3.加入list
            newsIndexVOList.add(newsIndexVO);
        }
        //再一次封装，经过NewsDTO,NewsIndexVO,ResultVo，3次封装
        return new ResultVo(NewsStateEnum.SUCCESS,newsIndexVOList);
    }

    /**
     * 查4个类别
     * 每个类别1个（newsIndexVO,newsIndexDTO）,5个（newsIndexVO,author）
     * 封装进resultVO.data的object↓
     * List<NewsIndex04VO>，(category,newsIndexVO(newsIndexDTO),List<newsIndexVO(author)> )
     */
    @GetMapping("/news/indexData04")
    public ResultVo findNewToIndex04(){
        //1.查表中前4个新闻类别
        List<NewsCategory> newsCategoryList = newsCategoryService.findLimit(4);
        List<NewsIndex04VO> newsIndex04VOList = new ArrayList<NewsIndex04VO>();
        //2.用每个新闻类别id查第一个新闻，放进左新闻
        for (NewsCategory newsCategory : newsCategoryList){
             List<NewsDTO> newsDTOList = newsService.findByCategoryId02(newsCategory.getNewsCategoryId(),0,6);
             NewsIndex04VO newsIndex04VO = new NewsIndex04VO();
            //一.封装newsIndex04VO.category
             newsIndex04VO.setCategory(newsCategory.getNewsCategoryName());
             int i =1;
             List<NewsIndexVO> newsIndexVOList = new ArrayList<NewsIndexVO>();
             for (NewsDTO newsDTO : newsDTOList){
                 //封装newsIndex04VO.leftNews和rightNewsList
                 NewsIndexVO newsIndexVO = new NewsIndexVO();
                 newsIndexVO.setNewsId(newsDTO.getNewsId());
                 newsIndexVO.setImageUrl(newsDTO.getImageUrl());
                 newsIndexVO.setTitle(newsDTO.getTitle());
                 newsIndexVO.setCreateTime(newsDTO.getCreateTime());
                 if (i==1){
                     //3.1封装newsIndex04VO.leftNews.NewsIndexDTO.
                     NewsIndexDTO newsIndexDTO = new NewsIndexDTO();
                     newsIndexDTO.setIntroduce(newsDTO.getIntroduce());
                     newsIndexDTO.setCategory(newsDTO.getCategory().getNewsCategoryName());
                     newsIndexDTO.setAuthor(newsDTO.getAuthor().getAvatar());
                     newsIndexVO.setData(newsIndexDTO);
                     //二装newsIndex04VO.LeftNews
                     newsIndex04VO.setLeftNews(newsIndexVO);
                 }else{
                     //3.2封装newsIndex04VO.rightNewsList
                     Map<String,String> map = new HashMap<>();
                     map.put("author",newsDTO.getAuthor().getAvatar());
                     newsIndexVO.setData(map);
                     newsIndexVOList.add(newsIndexVO);
                 }
                 i++;
             }
             //三装newsIndex04VO.RightNewsList
            newsIndex04VO.setRightNewsList(newsIndexVOList);
             //四装newsIndex04VOList
            newsIndex04VOList.add(newsIndex04VO);
        }
        return new ResultVo(NewsStateEnum.SUCCESS,newsIndex04VOList);
    }
}
