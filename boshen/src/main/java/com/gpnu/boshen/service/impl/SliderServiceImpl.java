package com.gpnu.boshen.service.impl;

import com.gpnu.boshen.dto.FileInfo;
import com.gpnu.boshen.dto.SliderDTO;
import com.gpnu.boshen.dynamic.Data;
import com.gpnu.boshen.entity.Slider;
import com.gpnu.boshen.mapper.SliderMapper;
import com.gpnu.boshen.service.SliderService;
import com.gpnu.boshen.util.FileUploadUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
public class SliderServiceImpl implements SliderService{
    @Autowired
    SliderMapper sliderMapper;

    @Override
    @Transactional
    public int addSlider(SliderDTO sliderDTO) {
        File dest = new File("");
        try{
            //1.上传图片
            FileInfo fileInfo = FileUploadUtil.systemImage(sliderDTO.getImage());
            if (fileInfo == null){
                return 0;
            }
            dest = new File(fileInfo.getFrontPath() + fileInfo.getBackPath());
            //2.添加轮播图类
            Slider slider = new Slider();
            slider.setUri(fileInfo.getBackPath());
            BeanUtils.copyProperties(sliderDTO,slider);
            return sliderMapper.insertSlider(slider);

        }catch (Exception e){
            dest.delete();
            throw new RuntimeException("轮播图添加错误："+ e.getMessage());
        }

    }

    @Override
    @Transactional
    public int deleteSlider(int id) {
        try{
            String uri = sliderMapper.findbyid(id).getUri();
            //1.删类
            sliderMapper.deleteSlider(id);
            //2.删图
            File file = new File(Data.UPLOAD_IMAGE_PATH + uri);
            file.delete();
            return 1;
        }catch (Exception e){
            throw new RuntimeException("删除轮播图失败：" + e.getMessage());
        }
    }
}
