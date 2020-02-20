package com.gpnu.boshen.service.impl;

import com.gpnu.boshen.entity.User;
import com.gpnu.boshen.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void registerUser() {
        User user = new User();
        user.setMail("test02");
        user.setUserName("ming");
        user.setPassword("123456");
        user.setPhone("123");
        System.out.println(userService.registerUser(user));
    }

    @Test
    void login() {
        User user = new User();
        user.setMail("test02");
        user.setPassword("123456");
        System.out.println(userService.login(user));
    }

    @Test
    void userInfo() {
        System.out.println(userService.userInfo(1));
    }

    @Test
    void updateUser(){
        User user = new User();
        user.setUserId(1);
        user.setMail("test0");
        user.setUserName("test");
        user.setPassword("test");
        user.setPhone("0");
        user.setAvatar("test");
        System.out.println(userService.updateUser(user));
    }
}