package com.gpnu.boshen.service;

import com.gpnu.boshen.dto.UserInfo;
import com.gpnu.boshen.entity.User;
import com.gpnu.boshen.vo.ResultVo;


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
    ResultVo updateAvatar(UserInfo userInfo);
}
