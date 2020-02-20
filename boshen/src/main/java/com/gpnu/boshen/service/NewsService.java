package com.gpnu.boshen.service;

import com.gpnu.boshen.dto.NewsInfo;
import com.gpnu.boshen.entity.News;
import com.gpnu.boshen.vo.ResultVo;
import org.omg.PortableInterceptor.INACTIVE;

public interface NewsService {

    //添加新闻
    public ResultVo addNews(NewsInfo newsInfo);

    //删除新闻
    public ResultVo deleteNews(int newsId);

    //查询新闻，通过newsid,作者id,类型id，是否为轮播图，标题的模糊化，
    public ResultVo findByNews(News news);

    //分页查询
    public ResultVo findByPage(String title,int pageStart,int quantity);

    //设置轮播图
    public ResultVo updateIsSlider(News news);
}
