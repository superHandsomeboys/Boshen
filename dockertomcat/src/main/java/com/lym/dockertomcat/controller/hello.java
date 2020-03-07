package com.lym.dockertomcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class hello {

    @GetMapping("/hellolym")
    public String hello(){
        System.out.println("hello启动");
        return "/hello.html";
    }
}
