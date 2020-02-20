package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.Ttag;
import com.gpnu.boshen.mapper.TtagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TtagController {

    @Autowired
    private TtagMapper ttagMapper;

    @GetMapping("/train/tags")
    public List<Ttag> list() {
        return ttagMapper.list();
    }

    @PostMapping("/train/tag")
    public Ttag insert(Ttag ttag) {
        return ttagMapper.insert(ttag);
    }

    @GetMapping("/train/tag/{id}")
    public String toEditPage(@PathVariable("id") int id, Model model) {
        Ttag train_tag = ttagMapper.get(id);
        model.addAttribute("train_tag", train_tag);

        return "/train/tag/add";
    }

    @PutMapping("/train/tag")
    public Ttag update(Ttag ttag) {
        return ttagMapper.update(ttag);
    }

    @DeleteMapping("/train/tag/{id}")
    public Ttag delete(@PathVariable("id") int id) {
        return ttagMapper.delete(id);
    }
}
