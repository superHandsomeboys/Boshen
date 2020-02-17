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
//备注
@Controller
public class SliderController {

    @Autowired
    SliderMapper sliderMapper;

    @GetMapping("/index")
    public String list(Model model) {
        List<Slider> sliders = sliderMapper.list();

        model.addAttribute("sliders", sliders);

        return "index";
    }

//    @GetMapping("/dept")
//    public Slider insertSlider(Slider slider) {
//        sliderMapper.insertSlider(slider);
//        return slider;
//    }
}
