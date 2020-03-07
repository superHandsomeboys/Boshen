package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum CompanyStatusEnum {
    SUCCESS(1,"成功"),
    FAIL_NULL_PARAM(-1001,"含空参数");

    private int state;
    private String msg;

    private CompanyStatusEnum(int state, String msg){
        this.state = state;
        this.msg =msg;
    }

    /**
     * 依据传入的state数字，返回相应的enum
     * @param state
     * @return
     */
    public static CompanyStatusEnum stateOf(int state){
        for(CompanyStatusEnum companyStatusEnum : values()){
            if (companyStatusEnum.getState() == state){
                return companyStatusEnum;
            }
        }
        return null;
    }
}