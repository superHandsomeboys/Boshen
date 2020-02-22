package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.ConsultCategory;
import com.gpnu.boshen.mapper.ConsultCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ConsultCategoryController {

    @Autowired
    private ConsultCategoryMapper consultCategoryMapper;

    /**
     * 查询所有咨询类别
     * @return
     */
    @GetMapping("/consult/categories")
    public List<ConsultCategory> list() {
        return consultCategoryMapper.list();
    }

    /**
     * 添加咨询类别
     * @param category
     * @return
     */
    @PostMapping("/consult/category")
    public ConsultCategory insert(ConsultCategory category) {
        return consultCategoryMapper.insert(category);
    }

    /**
     * 根据id 查询咨询类别
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/consult/category/{id}")
    public String toEditPage(@PathVariable("id") int id, Model model) {
        ConsultCategory consultCategory = consultCategoryMapper.get(id);
        model.addAttribute("consultCategory", consultCategory);

        return "/consult/category/add";
    }

    /**
     * 删除咨询类别
     * @param id
     * @return
     */
    @DeleteMapping("/consult/category/{id}")
    public ConsultCategory delete(@PathVariable("id") int id) {
        return consultCategoryMapper.delete(id);
    }
}
