package com.example.demo.service;

import com.example.demo.dto.UserAvatarInfo;
import com.example.demo.entity.User;
import com.example.demo.vo.ResultVo;


public interface UserService {
    //注册用户
    ResultVo registerUser(User user);

    //登录用户
    ResultVo login(User user);

    //根据id查询用户信息
    ResultVo userInfo(int userId);

    //根据userId更新user
    ResultVo updateUser(User user);

    //换头像
    ResultVo updateAvatar(UserAvatarInfo userAvatarInfo);
}
