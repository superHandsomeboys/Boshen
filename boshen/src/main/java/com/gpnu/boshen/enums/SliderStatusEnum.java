package com.gpnu.boshen.enums;

import lombok.Getter;

@Getter
public enum SliderStatusEnum {
    SUCCESS(1,"成功"),
    FAIL_NULL_PARAM(-1001,"含空参数"),FAIL_NOT_IMAGE(-1001,"不是jpg或png图片");

    private int state;
    private String msg;

    private SliderStatusEnum(int state, String msg){
        this.state = state;
        this.msg =msg;
    }

    /**
     * 依据传入的state数字，返回相应的enum
     * @param state
     * @return
     */
    public static SliderStatusEnum stateOf(int state){
        for(SliderStatusEnum sliderStatusEnum : values()){
            if (sliderStatusEnum.getState() == state){
                return sliderStatusEnum;
            }
        }
        return null;
    }
}