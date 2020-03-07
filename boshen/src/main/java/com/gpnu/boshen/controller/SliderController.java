package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.Slider;
import com.gpnu.boshen.mapper.SliderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SliderController {

    @Autowired
    SliderMapper sliderMapper;

    @GetMapping("/index")
    public String list(Model model) {
        List<Slider> sliders = sliderMapper.list();

        model.addAttribute("sliders", sliders);

        return "新闻发布页面";
    }

//    @GetMapping("/dept")
//    public Slider insertSlider(Slider slider) {
//        sliderMapper.insertSlider(slider);
//        return slider;
//    }
}
