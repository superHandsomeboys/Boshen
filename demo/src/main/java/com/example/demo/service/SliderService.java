package com.example.demo.service;

import com.example.demo.dto.SliderDTO;

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
