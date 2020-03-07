package com.example.demo.service.impl;

import com.example.demo.dto.FileInfo;
import com.example.demo.dto.NewsDTO;
import com.example.demo.dto.NewsInfo;
import com.example.demo.dynamic.Data;
import com.example.demo.entity.*;
import com.example.demo.enums.NewsStateEnum;
import com.example.demo.mapper.*;
import com.example.demo.service.NewsService;
import com.example.demo.util.FileUploadUtil;
import com.example.demo.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;


@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    NewsCategoryMapper newsCategoryMapper;
    @Autowired
    CommentMapper commentMapper;

    @Override
    @Transactional      //事务管理起来
    public ResultVo addNews(NewsInfo newsInfo) {
        File dest = new File("");  //外放是给catch用
        try {
            FileInfo fileInfo = FileUploadUtil.image(newsInfo.getAuthorId(),newsInfo.getImage());
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
            news.setCommentQuantity(0);
            news.setIntroduce(newsInfo.getIntroduce());
            news.setCreateTime(new Date());
            //4.保存文章，返回articleId,set
            Article article = new Article();
            article.setContent(newsInfo.getArticle());
            articleMapper.addArticle(article);
            news.setArticleId(article.getArticleId());
            //5.addNews
            int result = newsMapper.addNews(news);
            if(result <= 0){

            }
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
        File image = new File(Data.UPLOAD_IMAGE_PATH + news.getImageUrl());

        try{    //因为外键关系，新闻最先删，又图片一旦删了就不能恢复，所以图片最后
            //删除评论
            Comment comment = new Comment();
            comment.setNewsId(newsId);
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
            int resultDN  = newsMapper.deleteNews(newsId);
            //删内容
            int resultDA = articleMapper.deleteArticle(news.getArticleId());
            //删图片
            image.delete();
            return new ResultVo(NewsStateEnum.SUCCESS);
        }catch(Exception e){
            throw new RuntimeException("删除新闻错误"+e.getMessage());
        }
    }


    @Override
    public List<News> findByNews(News news) {
        if (news.getTitle()!= null){
            news.setTitle("%" + news.getTitle() + "%");
        }
        return newsMapper.findByNews(news);
    }



    @Override
    public List<News> findByPageTitle(String title, int page) {
        title = "%"+title+"%";  //模糊查询
        int start = (page-1) * Data.QUANTITY_IN_PAGE;
        return newsMapper.findByPage(title,start,Data.QUANTITY_IN_PAGE);
    }

    @Override
    public int findPageQuantityByTitle(String title) {
        String t = "%" + title + "%";
        int size = newsMapper.findByTitle(t).size();
        if (size == 0){
            return 0;
        }else if (size % Data.QUANTITY_IN_PAGE == 0){   //刚好整页
            return size / Data.QUANTITY_IN_PAGE;
        }
        return size / Data.QUANTITY_IN_PAGE +1;
    }

    @Override
    public ResultVo updateIsSlider(News news){
        newsMapper.updateIsSlider(news);
        return new ResultVo(NewsStateEnum.SUCCESS);
    }

    @Override
    public ResultVo findDetailedNewsVOByNewsId(Integer newsId) {
        DetailedNewsVO dnv = new DetailedNewsVO();
        //1.用newsid获取新闻类
        News cur = new News();
        cur.setNewsId(newsId);
        News news1 = newsMapper.findByNews(cur).get(0);
        dnv.setTitle(news1.getTitle());
        dnv.setCreateTime(news1.getCreateTime());
        dnv.setImageUrl(news1.getImageUrl());
        dnv.setCommentQuantity(news1.getCommentQuantity());
        //2.用authorId获取simpleUser
        User user = userMapper.findByUserId(news1.getAuthorId());
        SimpleUserVO suv = new SimpleUserVO();
        suv.setAvatarUrl(user.getAvatar());
        suv.setIntroduce(user.getIntroduce());
        suv.setUserName(user.getUserName());
        dnv.setAuthor(suv);
        //3.用categoryId获取categoryName
        NewsCategory newsCategory = newsCategoryMapper.findById(news1.getCategoryId());
        dnv.setCategory(newsCategory.getNewsCategoryName());
        //4.用articleId获取content
        dnv.setArticle(articleMapper.findArticleById(news1.getArticleId()).getContent());
        //5.获取newsId+1和newsId-1的simpleNews
        int lastNewsId = newsId - 1 ;
        int nextNewsId = newsId + 1 ;
        News cur2 = new News();
        cur2.setNewsId(lastNewsId);
        News lastNews = newsMapper.findByNews(cur2).get(0);
            //有上一个新闻
        if (lastNews != null){
            SimpleNewsVO lastSnv = new SimpleNewsVO();
            lastSnv.setNewsId(lastNews.getNewsId());
            lastSnv.setTitle(lastNews.getTitle());
            dnv.setLastNews(lastSnv);
        }
        cur2.setNewsId(nextNewsId);
        News nextNews = newsMapper.findByNews(cur2).get(0);
            //有下一个新闻
        if (nextNews != null){
            SimpleNewsVO nextSnv = new SimpleNewsVO();
            nextSnv.setNewsId(nextNews.getNewsId());
            nextSnv.setTitle(nextNews.getTitle());
            dnv.setNextNews(nextSnv);
        }
        //6.获取同类别下的4个simpleNews
        News news2 = new News();
        news2.setCategoryId(newsCategory.getNewsCategoryId());
        List<News> newsList = newsMapper.findByNews(news2);
        List<SimpleNewsVO> snvList = new ArrayList<SimpleNewsVO>();
        int number = 0;
        for (News news3: newsList){
            if (news3 == null || number>4){
                break;
            }
            number++;
            SimpleNewsVO simpleNewsVO = new SimpleNewsVO();
            simpleNewsVO.setTitle(news3.getTitle());
            simpleNewsVO.setNewsId(news3.getNewsId());
            simpleNewsVO.setCommentQuantity(news3.getCommentQuantity());
            simpleNewsVO.setCreateTime(news3.getCreateTime());
            simpleNewsVO.setImageUrl(news3.getImageUrl());
            simpleNewsVO.setIntroduce(news3.getIntroduce());
            snvList.add(simpleNewsVO);
        }
        dnv.setRelatedNewsList(snvList);

        //7.用newsId获取所有CommentVo
        Comment comment = new Comment();
        comment.setNewsId(newsId);
        List<Comment> parentCommentS = commentMapper.findById(comment);
        List<CommentVO> parentCommentVOList = new ArrayList<CommentVO>();
            //遍历父评论
        for (Comment parent : parentCommentS){
            CommentVO parentVO = new CommentVO();
            //封装父评论中的头像和名字
            User parentUser = userMapper.findByUserId(parent.getAuthorId());
            SimpleUserVO parentUserVO = new SimpleUserVO();
            parentUserVO.setUserName(parentUser.getUserName()) ;
            parentUserVO.setAvatarUrl(parentUser.getAvatar());
            parentVO.setAuthor(parentUserVO);
            //封装内容
            parentVO.setContent(parent.getContent());
            //封装id
            parentVO.setCommentId(parent.getCommentId());
            //封装评论时间
            parentVO.setCreateTime(parent.getCreateTime());

                //子评论list
            Comment comment2 = new Comment();
            comment2.setParentCommentId(parent.getCommentId());
            List<Comment> sonComments = commentMapper.findById(comment2);
            List<CommentVO> sonCommenVOtList = new ArrayList<CommentVO>(); //封装父评论下的子评论
            //遍历子评论
            for (Comment son : sonComments){
                CommentVO sonVO = new CommentVO();
                //封装父评论中的头像和名字
                User sonUser = userMapper.findByUserId(son.getAuthorId());
                SimpleUserVO sonUserVO = new SimpleUserVO();
                sonUserVO.setUserName(sonUser.getUserName()) ;
                sonUserVO.setAvatarUrl(sonUser.getAvatar());
                sonVO.setAuthor(sonUserVO);
                //封装内容
                sonVO.setContent(son.getContent());
                //封装id
                sonVO.setCommentId(son.getCommentId());
                //封装评论时间
                sonVO.setCreateTime(son.getCreateTime());
                sonCommenVOtList.add(sonVO);
            }
            //封装子评论list
            parentVO.setSonCommentList(sonCommenVOtList);
            //添加父评论
            parentCommentVOList.add(parentVO);
        }
        //给detailnewsVO封装评论
        dnv.setCommentList(parentCommentVOList);

        //8.封装评论数量
        return new ResultVo(NewsStateEnum.SUCCESS,dnv);
    }

    @Override
    public List<SimpleNewsVO> findLimit(Integer i) {
        List<News> newsList = newsMapper.findLimit(i);
        List<SimpleNewsVO> simpleNewsVOList = new ArrayList<SimpleNewsVO>();
        //把数据封装到simplenewsvolist
        for (News news: newsList){
            SimpleNewsVO simpleNewsVO = new SimpleNewsVO();
            BeanUtils.copyProperties(news,simpleNewsVO);
            simpleNewsVOList.add(simpleNewsVO);
        }
        return simpleNewsVOList;
    }

    @Override
    public List<SimpleNewsVO> findByIsSlider() {
        News news = new News();
        news.setIsSlider(true);
        List<News> newsList =newsMapper.findByNews(news);
        List<SimpleNewsVO> simpleNewsVOList = new ArrayList<SimpleNewsVO>();
        int i=0;
        for (News news1 : newsList){
            if (i>6){
                break;
            }
            i++;
            SimpleNewsVO simpleNewsVO =new SimpleNewsVO();
            simpleNewsVO.setNewsId(news1.getNewsId());;
            simpleNewsVO.setImageUrl(news1.getImageUrl());
            simpleNewsVO.setTitle(news1.getTitle());
            simpleNewsVOList.add(simpleNewsVO);
        }
        return simpleNewsVOList;
    }

    @Override
    public List<SimpleNewsVO> findByCategoryId(int categoryId, int i) {
        List<News> newsList = newsMapper.findByCategoryId(categoryId,i);
        List<SimpleNewsVO> simpleNewsVOList = new ArrayList<SimpleNewsVO>();
        for (News news : newsList){
            SimpleNewsVO simpleNewsVO = new SimpleNewsVO();
            simpleNewsVO.setTitle(news.getTitle());
            simpleNewsVO.setImageUrl(news.getImageUrl());
            simpleNewsVO.setNewsId(news.getNewsId());
            simpleNewsVO.setIntroduce(news.getIntroduce());
            simpleNewsVO.setCreateTime(news.getCreateTime());
            simpleNewsVO.setCommentQuantity(news.getCommentQuantity());
            simpleNewsVOList.add(simpleNewsVO);
        }
        return simpleNewsVOList;
    }

    @Override
    public List<SimpleNewsVO> findHotest(int i) {
        List<News> newsList =newsMapper.findHotest(i);
        List<SimpleNewsVO> simpleNewsVOList = new ArrayList<SimpleNewsVO>();
        for (News news : newsList){
            SimpleNewsVO simpleNewsVO = new SimpleNewsVO();
            simpleNewsVO.setTitle(news.getTitle());
            simpleNewsVO.setImageUrl(news.getImageUrl());
            simpleNewsVO.setNewsId(news.getNewsId());
            simpleNewsVO.setCommentQuantity(news.getCommentQuantity());
            simpleNewsVOList.add(simpleNewsVO);
        }
        return simpleNewsVOList;
    }

    @Override
    public List<SimpleNewsVO> findNewest(int i) {
        List<News> newsList =newsMapper.findNewest(i);
        List<SimpleNewsVO> simpleNewsVOList = new ArrayList<SimpleNewsVO>();
        for (News news : newsList){
            SimpleNewsVO simpleNewsVO = new SimpleNewsVO();
            simpleNewsVO.setTitle(news.getTitle());
            simpleNewsVO.setImageUrl(news.getImageUrl());
            simpleNewsVO.setNewsId(news.getNewsId());
            simpleNewsVO.setCreateTime(news.getCreateTime());
            simpleNewsVOList.add(simpleNewsVO);
        }
        return simpleNewsVOList;
    }



    @Override
    public List<NewsDTO> findNewsDTOLimit(int start, int quantity) {
        return toDTOs(newsMapper.findLimit01(start,quantity));
    }

    @Override
    public List<NewsDTO> findByCategoryId02(int categoryId, int start, int quantity) {
        return toDTOs(newsMapper.findByCategoryId01(categoryId,start,quantity));
    }

    @Override
    public List<PublicNews> findPublicNews() {
        //3.List<PublicNews>, (category,List<NewsIndexVO(author)> )
        //查询4个类别
        List<PublicNews> publicNewsList = new ArrayList<PublicNews>();
        for (NewsCategory newsCategory : newsCategoryMapper.findLimit(4)){
            PublicNews publicNews = new PublicNews();
            //1.装category
            publicNews.setCategory(newsCategory.getNewsCategoryName());
            //2.根据每个类别查3个新闻
            List<NewsDTO> newsDTOList = toDTOs(newsMapper.findByCategoryId01(newsCategory.getNewsCategoryId(),0,3));
            List<NewsIndexVO> newsIndexVOList = new ArrayList<NewsIndexVO>();
            for (NewsDTO newsDTO : newsDTOList){
                NewsIndexVO newsIndexVO = new NewsIndexVO();
                newsIndexVO.setCreateTime(newsDTO.getCreateTime());
                newsIndexVO.setTitle(newsDTO.getTitle());
                newsIndexVO.setImageUrl(newsDTO.getImageUrl());
                newsIndexVO.setNewsId(newsDTO.getNewsId());
                Map<String,String> map = new HashMap<>();
                map.put("author",newsDTO.getAuthor().getUserName());
                newsIndexVO.setData(map);
                newsIndexVOList.add(newsIndexVO);
            }
            //2.装List<NewsIndexVO>
            publicNews.setNewsIndexVOs(newsIndexVOList);
            publicNewsList.add(publicNews);
        }
        return publicNewsList;
    }

    @Override
    public NewsDTO findNewsByNewsId(int newsId) {
        News news = new News();
        news.setNewsId(newsId);
        return toDTO(newsMapper.findByNews(news).get(0));
    }


    /**
     * 把List<News>News转为NewsDTO
     */
    public List<NewsDTO> toDTOs(List<News> newsList){
        List<NewsDTO> newsDTOList = new ArrayList<NewsDTO>();
        for (News news : newsList){
            NewsDTO newsDTO = new NewsDTO();
            newsDTO.setTitle(news.getTitle());
            newsDTO.setNewsId(news.getNewsId());
            newsDTO.setIsSlider(news.getIsSlider());
            newsDTO.setIntroduce(news.getIntroduce());
            newsDTO.setImageUrl(news.getImageUrl());
            newsDTO.setCreateTime(news.getCreateTime());
            newsDTO.setCommentQuantity(news.getCommentQuantity());
            //2.封装article
            newsDTO.setArticle(articleMapper.findArticleById(news.getArticleId()));
            //3.封装author
            newsDTO.setAuthor(userMapper.findByUserId(news.getAuthorId()));
            //4.封装category
            newsDTO.setCategory(newsCategoryMapper.findById(news.getCategoryId()));
            newsDTOList.add(newsDTO);
        }
        return newsDTOList;
    }

    /**
     * 把News转newsDTO
     */
    public NewsDTO toDTO(News news){
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setTitle(news.getTitle());
        newsDTO.setNewsId(news.getNewsId());
        newsDTO.setIsSlider(news.getIsSlider());
        newsDTO.setIntroduce(news.getIntroduce());
        newsDTO.setImageUrl(news.getImageUrl());
        newsDTO.setCreateTime(news.getCreateTime());
        newsDTO.setCommentQuantity(news.getCommentQuantity());
        //2.封装article
        newsDTO.setArticle(articleMapper.findArticleById(news.getArticleId()));
        //3.封装author
        newsDTO.setAuthor(userMapper.findByUserId(news.getAuthorId()));
        //4.封装category
        newsDTO.setCategory(newsCategoryMapper.findById(news.getCategoryId()));
        return newsDTO;
    }
}
