package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum WikiStatusEnum {
    SUCCESS(1,"成功"),
    FAIL_NULL_PARAM(-1001,"含空参数");

    private int state;
    private String msg;

    private WikiStatusEnum(int state, String msg){
        this.state = state;
        this.msg =msg;
    }

    /**
     * 依据传入的state数字，返回相应的enum
     * @param state
     * @return
     */
    public static WikiStatusEnum stateOf(int state){
        for(WikiStatusEnum wikiStatusEnum : values()){
            if (wikiStatusEnum.getState() == state){
                return wikiStatusEnum;
            }
        }
        return null;
    }
}