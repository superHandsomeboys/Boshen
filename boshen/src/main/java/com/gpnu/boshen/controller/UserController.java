package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.User;
import com.gpnu.boshen.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users")
    public List<User> list() {
        return userMapper.list();
    }

    @PostMapping("/user")
    public User insert(User user) {
        return userMapper.insert(user);
    }

    @GetMapping("/user/{id}")
    public String toEditPage(@PathVariable("id") int id, Model model) {
        User user = userMapper.get(id);
        model.addAttribute("user", user);

        return "/user/add";
    }

    @PutMapping("/user")
    public User update(User user) {
        return userMapper.update(user);
    }

    @DeleteMapping("/user/{id}")
    public User delete(@PathVariable("id") int id) {
        return userMapper.delete(id);
    }
}
