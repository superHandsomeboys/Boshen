package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.Article;
import com.gpnu.boshen.mapper.ArticleMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {

    @Autowired
    private ArticleMapper articleMapper;

//    @RequestMapping("/article/{id}")
//    @GetMapping("/article/{id}")
//    public Article get(@PathVariable("id") int id) {
//        Article article = articleMapper.getArticleById(id);
//        model.addAttribute("articles", article);
//        return "redirect:/index";
//        return articleMapper.getArticleById(id);
//    }

    @PostMapping("/article")
    public Article insert(Article article) {
        return articleMapper.insertArticle(article);
    }

    @GetMapping("/article/{id}")
    public String toEditPage(@PathVariable("id") int id, Model model) {
        Article article = articleMapper.getArticleById(id);
        model.addAttribute("article", article);

        return "/article/add";
    }

    @PutMapping("/article")
    public Article update(Article article) {
        return articleMapper.update(article);
    }

    @Delete("/article/{id}")
    public Article delete(@PathVariable("id") int id) {
        return articleMapper.delete(id);
    }
}
