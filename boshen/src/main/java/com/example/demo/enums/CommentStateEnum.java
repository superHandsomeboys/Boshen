package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum CommentStateEnum {
    SUCCESS(1,"成功"),FAIL_ADD(-1,"添加新闻类型失败"),
    FAIL_CANT_COMMENT_YOURSELF(-1001,"不能回复自己的评论"),FAIL_NULL(-1002,"评论不存在"),
    FAIL_NULL_PARAM(-1003,"含空参数");

    private int state;
    private String msg;

    private CommentStateEnum(int state, String msg){
        this.state = state;
        this.msg =msg;
    }

    /**
     * 依据传入的state数字，返回相应的enum
     * @param state
     * @return
     */
    public static CommentStateEnum stateOf(int state){
        for(CommentStateEnum commentStateEnum : values()){
            if (commentStateEnum.getState() == state){
                return commentStateEnum;
            }
        }
        return null;
    }
}
