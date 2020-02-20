package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.TrainCategory;
import com.gpnu.boshen.mapper.TrainCategoryMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class TrainCategoryController {

    @Autowired
    private TrainCategoryMapper trainCategoryMapper;

    @GetMapping("/train/categories")
    public List<TrainCategory> list() {
        return trainCategoryMapper.list();
    }

    @PostMapping("/train/category")
    public TrainCategory insert(TrainCategory trainCategory) {
        return trainCategoryMapper.insert(trainCategory);
    }

    @GetMapping("/train/category/{id}")
    public String toEditPage(@PathVariable("id") int id, Model model) {
        TrainCategory trainCategory = trainCategoryMapper.get(id);
        model.addAttribute("trainCategory", trainCategory);

        return "/train/category/add";
    }

    @PutMapping("/train/category")
    public TrainCategory update(TrainCategory trainCategory) {
        return trainCategoryMapper.update(trainCategory);
    }

    @Delete("/train/category/{id}")
    public TrainCategory delete(@PathVariable("id") int id) {
        return trainCategoryMapper.delete(id);
    }
}
