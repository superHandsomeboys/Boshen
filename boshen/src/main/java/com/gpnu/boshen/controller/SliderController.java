package com.gpnu.boshen.controller;

import com.gpnu.boshen.dto.SliderDTO;
import com.gpnu.boshen.entity.Slider;
import com.gpnu.boshen.enums.ScienceStatusEnum;
import com.gpnu.boshen.enums.SliderStatusEnum;
import com.gpnu.boshen.mapper.SliderMapper;
import com.gpnu.boshen.service.SliderService;
import com.gpnu.boshen.util.FileUploadUtil;
import com.gpnu.boshen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
//备注
@RestController
public class SliderController {
    @Autowired
    SliderService sliderService;

    @Autowired
    SliderMapper sliderMapper;

    /**
     * 查询公司的所有轮播图
     * @return
     */
    @GetMapping("/slider/company")
    public ResultVo list() {
        return new ResultVo(SliderStatusEnum.SUCCESS,sliderMapper.list(0));
    }
    /**
     * 查询所有首页的轮播图
     */
    @GetMapping("/slider/index")
    public ResultVo sliderIndexList(){
        return new ResultVo(ScienceStatusEnum.SUCCESS,sliderMapper.list(1));
    }

    /**
     * 添加轮播图
     * @param sliderDTO
     * @return
     */
    @PostMapping("/slider")
    public ResultVo add(@RequestBody SliderDTO sliderDTO){
        if (sliderService.addSlider(sliderDTO) <= 0){
            return new ResultVo(SliderStatusEnum.FAIL_NOT_IMAGE);
        }
        return new ResultVo(SliderStatusEnum.SUCCESS);
    }


//    @PostMapping("/insertIslider")
//    public ResultVo insertI(MultipartFile file){
//        SliderDTO sliderDTO = new SliderDTO();
//        sliderDTO.setImage(file);
//        sliderDTO.setName("首页轮播图");
//        sliderDTO.setType(1);
//        if (sliderService.addSlider(sliderDTO) <= 0){
//            return new ResultVo(SliderStatusEnum.FAIL_NOT_IMAGE);
//        }
//        return new ResultVo(SliderStatusEnum.SUCCESS);
//    }
//    @PostMapping("/insertCslider")
//    public ResultVo insertC(MultipartFile file){
//        SliderDTO sliderDTO = new SliderDTO();
//        sliderDTO.setImage(file);
//        sliderDTO.setName("公司轮播图");
//        sliderDTO.setType(0);
//        if (sliderService.addSlider(sliderDTO) <= 0){
//            return new ResultVo(SliderStatusEnum.FAIL_NOT_IMAGE);
//        }
//        return new ResultVo(SliderStatusEnum.SUCCESS);
//    }

    /**
     * 删除轮播图
     * @param
     * @return
     */
    @DeleteMapping("/slider/{id}")
    public ResultVo deleteSlider(@PathVariable("id") Integer id) {
        sliderService.deleteSlider(id);
        return new ResultVo(SliderStatusEnum.SUCCESS);
    }
}
