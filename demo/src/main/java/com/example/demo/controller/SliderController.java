package com.example.demo.controller;

import com.example.demo.dto.SliderDTO;
import com.example.demo.enums.ScienceStatusEnum;
import com.example.demo.enums.SliderStatusEnum;
import com.example.demo.mapper.SliderMapper;
import com.example.demo.service.SliderService;
import com.example.demo.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
