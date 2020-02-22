package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.Train;
import com.gpnu.boshen.entity.TrainImage;
import com.gpnu.boshen.mapper.TrainImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TrainImageController {

    @Autowired
    private TrainImageMapper trainImageMapper;

    /**
     * 查询所有的培训图片
     * @return
     */
    @GetMapping("/train/images")
    public List<TrainImage> list() {
        return trainImageMapper.list();
    }

    /**
     * 添加培训图片
     * @param trainImage
     * @return
     */
    @PostMapping("/train/image")
    public TrainImage insert(TrainImage trainImage) {
        return trainImageMapper.insert(trainImage);
    }

    /**
     * 根据id查培训图
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/train/image/{id}")
    public String toEditPage(@PathVariable("id") int id, Model model) {
        List<TrainImage> trainImageList = trainImageMapper.get(id);
        model.addAttribute("trainImageList", trainImageList);

        return "/train/image/add";
    }

    /**
     * 更新培训图
     * @param trainImage
     * @return
     */
    @PutMapping("/train/image")
    public TrainImage update(TrainImage trainImage) {
        return trainImageMapper.update(trainImage);
    }

    /**
     * 删除培训图
     * @param id
     * @return
     */
    @DeleteMapping("/train/image/{id}")
    public TrainImage delete(@PathVariable("id") int id) {
        return trainImageMapper.delete(id);
    }
}
