package com.gpnu.boshen.service;

import com.gpnu.boshen.dto.ScienceDTO;
import com.gpnu.boshen.dto.ScienceRDTO;
import com.gpnu.boshen.entity.Science;
import com.gpnu.boshen.vo.ResultVo;

import java.util.List;

public interface ScienceService {

    /**
     * 添加科技文章
     */
    public int addScience(ScienceRDTO scienceRDTO);

    /**
     * 查某页的科技类，分页查询，8个科技类一页
     * @return
     */
    public List<ScienceDTO> findPage(int page);

    /**
     * 查询科技文章有多少页
     * @return
     */
    public int findPageQuantity();

    /**
     * 用scienceid查科技文章详情页的部分内容
     * @param scienceId
     * @return
     */
    public ScienceDTO findByScienceId(int scienceId);

    /**
     * 找到上一个科技文章，没有上一个就返回空
     * @param scienceId
     * @return
     */
    public Science lastScience(int scienceId);

    /**
     * 找到下一个科技文章类，没就返回空
     * @param scienceId
     * @return
     */
    public Science nextScience(int scienceId);

    /**
     * 查找start下标开始quantity个science
     * @return
     */
    public List<ScienceDTO> findLimit(int start ,int quantity);

    /**
     * 条件，分页查询
     */
    public List<Science> findByPageTitle(String title,int page);

    /**
     * 条件查询页数
     */
    public int findPageQuantityByTitle(String title);

    /**
     * 删除科技类
     */
    public void deleteScience(int id);

    /**
     * 根据作者id查询科技文章
     */
    public List<Science> findByAuthorId(int authorId);
}
