package com.gpnu.boshen.controller;

import com.gpnu.boshen.dto.UserInfo;
import com.gpnu.boshen.entity.User;
import com.gpnu.boshen.enums.UserStateEnum;
import com.gpnu.boshen.service.UserService;
import com.gpnu.boshen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
@SessionAttributes(value = {"userId"})
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/user")
    public ResultVo register(@RequestBody User user){
        if (user.getPhone() == null || user.getMail() == null || user.getUserName() == null
                || user.getPassword() == null ){
            return new ResultVo(UserStateEnum.FAIL_NULL_PARAM);
        }
        return userService.registerUser(user);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResultVo login(@RequestBody User user, Model model, HttpSession session){
        if (user.getPassword() == null || user.getMail() == null){
            return new ResultVo(UserStateEnum.FAIL_NULL_PARAM);
        }
        ResultVo resultVo =userService.login(user);
        User user1 = (User) resultVo.getData(); //从resultVo中拿取userId
        model.addAttribute("userId",user1.getUserId()); //放到session中
        resultVo.setData(null); //再制空
        return resultVo;
    }

    /**
     * 查询session中的userId对应的用户信息
     * @param model
     * @return
     */
    @GetMapping("/user")
    public ResultVo userInfo(Model model){
        Integer userId = (Integer) model.getAttribute("userId");
        //如果session没有userId
        if (userId == null){
            return new ResultVo(UserStateEnum.FAIL_USERID_NULL);
        }
        //查询
        ResultVo resultVo = userService.userInfo(userId);
        return resultVo;
    }

    /**
     * 更新用户文字信息
     * @param user
     * @return
     */
    @PutMapping("/user")
    public ResultVo updateUser(@RequestBody User user){
        if (user.getPhone() == null || user.getMail() == null || user.getUserName() == null
                || user.getPassword() == null ){
            return new ResultVo(UserStateEnum.FAIL_NULL_PARAM);
        }
        ResultVo resultVo = userService.updateUser(user);
        return resultVo;
    }

    /**
     * 更新用户权限
     * @param user
     * @return
     */
    @PutMapping("/user/userType")
    public ResultVo updateUserType(@RequestBody User user){
        if (user.getUserId() == null || user.getUserType() == null){
            return new ResultVo(UserStateEnum.FAIL_NULL_PARAM);
        }
        return userService.updateUser(user);
    }


    /**
     *  设置头像，默认头像,user
     */
    @PutMapping("/user/avatar")
    public ResultVo updateAvatar(@RequestBody UserInfo userInfo){
        if (userInfo.getAvatar() == null || userInfo.getUserId()==null){
            return new ResultVo(UserStateEnum.FAIL_NULL_PARAM);
        }
        return userService.updateAvatar(userInfo);
    }

//    //测试设置头像的接口，搭配file.html
//    @PostMapping("/update")
//    public ResultVo update(MultipartFile file){
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId(1);
//        userInfo.setAvatar(file);
//        return userService.updateAvatar(userInfo);
//        //测试1.格式错，2.事务回滚，2.正常过
//    }
}
