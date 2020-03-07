package com.gpnu.boshen.service;

import com.gpnu.boshen.dto.SliderDTO;

public interface SliderService {

    /**
     * 添加轮播图
     */
    int addSlider(SliderDTO sliderDTO);

    /**
     * 删除轮播图
     */
    int deleteSlider(int id);

}
