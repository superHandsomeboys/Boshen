package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.Consult;
import com.gpnu.boshen.mapper.ConsultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ConsultController {

    @Autowired
    private ConsultMapper consultMapper;

    @GetMapping("/consults")
    public List<Consult> list() {
        return consultMapper.list();
    }

    @PostMapping("/consult")
    public Consult insert(Consult consult) {
        return consultMapper.insert(consult);
    }

    @GetMapping("/consult/{id}")
    public String toEditPage(@PathVariable("id") int id, Model model) {
        Consult consult = consultMapper.get(id);
        model.addAttribute("consult", consult);

        return "/consult/add";
    }

    @PutMapping("/consult")
    public Consult update(Consult consult) {
        return consultMapper.update(consult);
    }

    @DeleteMapping("/consult/{id}")
    public Consult delete(int id) {
        return consultMapper.delete(id);
    }
}
