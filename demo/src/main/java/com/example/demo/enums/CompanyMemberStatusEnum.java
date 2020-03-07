package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum CompanyMemberStatusEnum {
    SUCCESS(1,"成功"),
    FAIL_NULL_PARAM(-1001,"含空参数");

    private int state;
    private String msg;

    private CompanyMemberStatusEnum(int state, String msg){
        this.state = state;
        this.msg =msg;
    }

    /**
     * 依据传入的state数字，返回相应的enum
     * @param state
     * @return
     */
    public static CompanyMemberStatusEnum stateOf(int state){
        for(CompanyMemberStatusEnum companyMemberStatusEnum : values()){
            if (companyMemberStatusEnum.getState() == state){
                return companyMemberStatusEnum;
            }
        }
        return null;
    }
}