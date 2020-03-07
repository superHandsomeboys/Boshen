package com.gpnu.boshen.enums;

import lombok.Getter;

@Getter
public enum NewsStateEnum {
    SUCCESS(1,"成功"),FAIL_ADD(-1,"添加新闻败"),
    FAIL_NOT_IMAGE(-1001,"不是jpg或png图片"),FAIL_NULL_PARAM(-1002,"含空参数");

    private int state;
    private String msg;

    private NewsStateEnum(int state, String msg){
        this.state = state;
        this.msg =msg;
    }

    /**
     * 依据传入的state数字，返回相应的enum
     * @param state
     * @return
     */
    public static NewsStateEnum stateOf(int state){
        for(NewsStateEnum newsStateEnum : values()){
            if (newsStateEnum.getState() == state){
                return newsStateEnum;
            }
        }
        return null;
    }
}
