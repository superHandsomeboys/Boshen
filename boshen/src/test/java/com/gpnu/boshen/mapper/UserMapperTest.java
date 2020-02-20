package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void findByMail() {
        System.out.println(userMapper.findByMail("test01"));
    }

    @Test
    void findByPhone() {
        System.out.println(userMapper.findByPhone("123456"));
    }

    @Test
    void addUser() {
        User user = new User();
        user.setMail("test01");
        user.setUserName("ming");
        user.setPassword("123456");
        user.setPhone("123");
        userMapper.addUser(user);
    }

    @Test
    void findByMailPassword() {
        User user = userMapper.findByMailPassword("test02","123456");
        System.out.println(user);
    }

    @Test
    void findByUserId() {
        User user = userMapper.findByUserId(1);
        System.out.println(user);
    }

    @Test
    void updateUser(){
        User user = new User();
        user.setUserId(1);
        user.setUserType(2);
//        user.setMail("test");
//        user.setUserName("test");
//        user.setPassword("test");
//        user.setPhone("test");
//        user.setAvatar("test");
        userMapper.updateUser(user);
    }
}