package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum ScienceStatusEnum {
    SUCCESS(1,"成功"),
    FAIL_NULL_PARAM(-1001,"含空参数"),FAIL_NOT_IMAGE(-1002,"不是jpg或png图片");

    private int state;
    private String msg;

    private ScienceStatusEnum(int state, String msg){
        this.state = state;
        this.msg =msg;
    }

    /**
     * 依据传入的state数字，返回相应的enum
     * @param state
     * @return
     */
    public static ScienceStatusEnum stateOf(int state){
        for(ScienceStatusEnum scienceStatusEnum : values()){
            if (scienceStatusEnum.getState() == state){
                return scienceStatusEnum;
            }
        }
        return null;
    }
}