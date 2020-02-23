package com.gpnu.boshen.service;

import com.gpnu.boshen.dto.NewsDTO;
import com.gpnu.boshen.dto.NewsInfo;
import com.gpnu.boshen.entity.News;
import com.gpnu.boshen.vo.NewsIndexVO;
import com.gpnu.boshen.vo.ResultVo;
import com.gpnu.boshen.vo.SimpleNewsVO;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface NewsService {

    //添加新闻
    public ResultVo addNews(NewsInfo newsInfo);

    //删除新闻
    public ResultVo deleteNews(int newsId);

    //查询新闻，通过newsid,作者id,类型id，是否为轮播图，标题的模糊化，
    public List<News> findByNews(News news);

    //分页查询
    public ResultVo findByPage(String title,int pageStart,int quantity);

    //设置轮播图
    public ResultVo updateIsSlider(News news);

    //根据newsid查询新闻详情页的所有数据
    public ResultVo findDetailedNewsVOByNewsId(Integer newsId);

    //查询i个新闻，如果i<=0，查询所有
    public List<SimpleNewsVO> findLimit(Integer i);

    //查轮播图的新闻
    public List<SimpleNewsVO> findByIsSlider();

    //根据类别id，查前i个新闻
    public List<SimpleNewsVO> findByCategoryId(int category,int i);

    //查找最热门的前i个新闻
    public List<SimpleNewsVO> findHotest(int i);

    //查找最新的前i个新闻
    public List<SimpleNewsVO> findNewest(int i);

    //从下标start开始，查找个quantity个新闻，封装NewsDTO中
    public List<NewsDTO> findNewsDTOLimit(int start,int quantity);

    //根据categoryId,从下标start开始，查找个quantity个新闻，封装NewsDTO中
    public List<NewsDTO> findByCategoryId02(int categoryId,int start ,int quantity);
}
