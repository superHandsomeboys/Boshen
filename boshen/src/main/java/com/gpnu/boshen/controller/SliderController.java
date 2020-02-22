package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.Slider;
import com.gpnu.boshen.mapper.SliderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class SliderController {

    @Autowired
    SliderMapper sliderMapper;

    /**
     * 查询一类型的所有轮播图
     * @return
     */
    @GetMapping("/index")
    public String list(Model model) {
        List<Slider> sliders = sliderMapper.list();

        model.addAttribute("sliders", sliders);

        return "index";
    }

    /**
     * 添加轮播图
     * @param slider
     * @return
     */

    /**
     * 删除轮播图
     * @param sliderId
     * @return
     */

//    @GetMapping("/dept")
//    public Slider insertSlider(Slider slider) {
//        sliderMapper.insertSlider(slider);
//        return slider;
//    }
}
