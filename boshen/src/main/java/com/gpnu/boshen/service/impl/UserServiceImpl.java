package com.gpnu.boshen.service.impl;

import com.gpnu.boshen.dto.FileInfo;
import com.gpnu.boshen.dto.UserInfo;
import com.gpnu.boshen.dynamic.Data;
import com.gpnu.boshen.entity.User;
import com.gpnu.boshen.enums.UserStateEnum;
import com.gpnu.boshen.mapper.UserMapper;
import com.gpnu.boshen.service.UserService;
import com.gpnu.boshen.util.FileUploadUtil;
import com.gpnu.boshen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    //vo
    ResultVo resultVo;

    @Override
    public ResultVo registerUser(User user) {
        //检验mail是否已有
        if (userMapper.findByMail(user.getMail()) != null) {
            //如果重复,设置状态信息，封装成VO
            resultVo = new ResultVo(UserStateEnum.FAIL_MAIL_REPEAT);
            return resultVo;
        }
        //检验phone是否已有
        if (userMapper.findByPhone(user.getPhone()) != null) {
            //如果重复,设置状态信息，封装成VO
            resultVo = new ResultVo(UserStateEnum.FAIL_PHONE_REPEAT);
            return resultVo;
        }
        //保存
        user.setCreateTime(new Date()); //比中国时间少8h
        user.setUserType(0);    //默认普通用户
        user.setAvatar(Data.DEFAULT_AVATAR);
        if (userMapper.addUser(user) > 0) {
            resultVo = new ResultVo(UserStateEnum.SUCCESS);
            return resultVo;
        }
        resultVo = new ResultVo(UserStateEnum.FAIL_ADD);
        return resultVo;

    }

    @Override
    public ResultVo login(User user) {
        //验证邮箱
        User user2 = userMapper.findByMail(user.getMail());
        //未注册
        if (user2 == null){
            resultVo = new ResultVo(UserStateEnum.FAIL_NOT_REGISTER);
            return resultVo;
        }
        //同时验证邮箱和密码
        User user1 = userMapper.findByMailPassword(user.getMail(),user.getPassword());
        //密码错误
        if (user1 == null){
            resultVo = new ResultVo(UserStateEnum.FAIL_PASSWORD_ERROR);
            return resultVo;
        }
        //登录成功
        resultVo = new ResultVo(UserStateEnum.SUCCESS);
        resultVo.setData(user1);    //先把类带过去controller
//        Map<String, Integer> map = new HashMap<>();
//        map.put("userId", user1.getUserId());
//        resultVo.setData(map);

        return resultVo;
    }

    @Override
    public ResultVo userInfo(int userId) {
        User user = userMapper.findByUserId(userId);
        resultVo = new ResultVo(UserStateEnum.SUCCESS);
        resultVo.setData(user);
        return resultVo;
    }

    @Override
    public ResultVo updateUser(User user) {
        if (user.getMail() != null){
            //检验mail是否已有,排除自己
            User user1 = userMapper.findByMail(user.getMail());
            if (user1 != null && user1.getUserId()!=user.getUserId()) {
                //如果重复,设置状态信息，封装成VO
                resultVo = new ResultVo(UserStateEnum.FAIL_MAIL_REPEAT);
                return resultVo;
            }
        }
        if (user.getPhone() != null){
            //检验phone是否已有,排除自己
            User user2 = userMapper.findByPhone(user.getPhone());
            if (user2 != null && user2.getUserId()!=user.getUserId()) {
                //如果重复,设置状态信息，封装成VO
                resultVo = new ResultVo(UserStateEnum.FAIL_PHONE_REPEAT);
                return resultVo;
            }
        }
        //检验完毕即可更新
        int result = userMapper.updateUser(user);
        if (result <=0){
            //没有这个用户
            resultVo = new ResultVo(UserStateEnum.FAIL_NULL);
            return resultVo;
        }
        //成功
        resultVo = new ResultVo(UserStateEnum.SUCCESS);
        return resultVo;
    }

    @Override
    @Transactional
    public ResultVo updateAvatar(UserInfo userInfo) {
        File file = new File("");   //用于catch
        try{
            int userId = userInfo.getUserId();
            //1.上传头像至本地
            FileInfo fileInfo = FileUploadUtil.image(userId,userInfo.getAvatar());
            if (fileInfo == null){
                return new ResultVo(UserStateEnum.FAIL_IMAGE_FORM_WRONG);
            }
            file = new File(fileInfo.getFrontPath()+fileInfo.getBackPath()); //用于catch
            //2.更新user
            User user = new User();
            user.setUserId(userId);
            user.setAvatar(fileInfo.getBackPath());
            userMapper.updateUser(user);
            ResultVo resultVo = new ResultVo(UserStateEnum.SUCCESS);
            Map<String, String> map = new HashMap<>();
            map.put("avatarUrl", user.getAvatar());
            resultVo.setData(map);
            return resultVo;

        }catch (Exception e){
            file.delete();  //保持事务一致性
            throw new RuntimeException("头像上传异常："+e.getMessage());
        }

    }


}
