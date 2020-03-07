package com.gpnu.boshen.enums;

import lombok.Getter;

@Getter
public enum UserStateEnum {
    SUCCESS(1,"成功"),FAIL_MAIL_REPEAT(-1001,"邮箱已被注册"),FAIL_PHONE_REPEAT(-1002,"手机号已被注册"),
    FAIL_ADD(-1,"添加用户失败"),FAIL_NULL(-2,"用户为空"),FAIL_NOT_REGISTER(-1003,"邮箱未注册"),
    FAIL_PASSWORD_ERROR(-1004,"密码错误"),FAIL_USERID_NULL(-1005,"session中的userId为空"),
    FAIL_UPDATE_USER(-1006,"更新用户信息失败"),FAIL_NULL_PARAM(-1007,"含空参数"),
    FAIL_IMAGE_FORM_WRONG(-1008,"请选择jpg和png格式图片"),ORDINARY_USER(2003,"普通用户"),
    COMPANY_USER(2002,"公司员工"),ROOT_USER(2001,"超级管理员");

    private int state;
    private String msg;

    private UserStateEnum(int state, String msg){
        this.state = state;
        this.msg =msg;
    }

    /**
     * 依据传入的state数字，返回相应的enum
     * @param state
     * @return
     */
    public static UserStateEnum stateOf(int state){
        for(UserStateEnum userStateEnum : values()){
            if (userStateEnum.getState() == state){
                return userStateEnum;
            }
        }
        return null;
    }
}
