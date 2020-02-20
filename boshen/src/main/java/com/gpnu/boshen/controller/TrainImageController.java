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

    @GetMapping("/train/images")
    public List<TrainImage> list() {
        return trainImageMapper.list();
    }

    @PostMapping("/train/image")
    public TrainImage insert(TrainImage trainImage) {
        return trainImageMapper.insert(trainImage);
    }

    @GetMapping("/train/image/{id}")
    public String toEditPage(@PathVariable("id") int id, Model model) {
        List<TrainImage> trainImageList = trainImageMapper.get(id);
        model.addAttribute("trainImageList", trainImageList);

        return "/train/image/add";
    }

    @PutMapping("/train/image")
    public TrainImage update(TrainImage trainImage) {
        return trainImageMapper.update(trainImage);
    }

    @DeleteMapping("/train/image/{id}")
    public TrainImage delete(@PathVariable("id") int id) {
        return trainImageMapper.delete(id);
    }
}
