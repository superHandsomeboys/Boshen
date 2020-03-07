package com.gpnu.boshen.enums;

import lombok.Getter;

@Getter
public enum ConsultCategoryStateEnum {
    SUCCESS(1,"成功"),
    FAIL_NULL_PARAM(-1001,"含空参数");

    private int state;
    private String msg;

    private ConsultCategoryStateEnum(int state, String msg){
        this.state = state;
        this.msg =msg;
    }

    /**
     * 依据传入的state数字，返回相应的enum
     * @param state
     * @return
     */
    public static ConsultCategoryStateEnum stateOf(int state){
        for(ConsultCategoryStateEnum consultCategoryStateEnum : values()){
            if (consultCategoryStateEnum.getState() == state){
                return consultCategoryStateEnum;
            }
        }
        return null;
    }
}
