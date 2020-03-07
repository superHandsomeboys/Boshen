package com.gpnu.boshen.enums;

import lombok.Getter;

@Getter
public enum NewsCategoryStateEnum {
    SUCCESS(1,"成功"),FAIL_ADD(-1,"添加新闻类型失败"),
    FAIL_NULL(-3,"没有此新闻类型"),FAIL_REPEAT_NAME(-1001,"新闻类别已存在"),
    FAIL_NULL_PARAM(-1002,"含空参数");

    private int state;
    private String msg;

    private NewsCategoryStateEnum(int state, String msg){
        this.state = state;
        this.msg =msg;
    }

    /**
     * 依据传入的state数字，返回相应的enum
     * @param state
     * @return
     */
    public static NewsCategoryStateEnum stateOf(int state){
        for(NewsCategoryStateEnum newsCategoryStateEnum : values()){
            if (newsCategoryStateEnum.getState() == state){
                return newsCategoryStateEnum;
            }
        }
        return null;
    }
}
