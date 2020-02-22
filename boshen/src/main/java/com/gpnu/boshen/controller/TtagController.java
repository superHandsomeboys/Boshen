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

    /**
     * 查询所有的培训标签
     * @return
     */
    @GetMapping("/train/tags")
    public List<Ttag> list() {
        return ttagMapper.list();
    }

    /**
     * 添加标签
     * @param ttag
     * @return
     */
    @PostMapping("/train/tag")
    public Ttag insert(Ttag ttag) {
        return ttagMapper.insert(ttag);
    }

    /**
     * 根据id查询标签
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/train/tag/{id}")
    public String toEditPage(@PathVariable("id") int id, Model model) {
        Ttag train_tag = ttagMapper.get(id);
        model.addAttribute("train_tag", train_tag);

        return "/train/tag/add";
    }

    /**
     * 更新
     * @param ttag
     * @return
     */
    @PutMapping("/train/tag")
    public Ttag update(Ttag ttag) {
        return ttagMapper.update(ttag);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/train/tag/{id}")
    public Ttag delete(@PathVariable("id") int id) {
        return ttagMapper.delete(id);
    }
}
