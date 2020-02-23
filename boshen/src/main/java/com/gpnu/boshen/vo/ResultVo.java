package com.gpnu.boshen.vo;
import com.gpnu.boshen.entity.News;
import com.gpnu.boshen.entity.NewsCategory;
import com.gpnu.boshen.enums.*;
import lombok.Data;

/**
 * view object,返回给前端的json
 */


@Data
public class ResultVo<T> {
    /**
     * 状态码
     */
    private Integer state;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体内容
     */
    private T data;
    //user类的vo构造器
    public ResultVo(UserStateEnum userStateEnum){
        this.state = userStateEnum.getState();
        this.msg = userStateEnum.getMsg();
    }

    public ResultVo(UserStateEnum userStateEnum,T data){
        this.state = userStateEnum.getState();
        this.msg = userStateEnum.getMsg();
        this.data = data;
    }

    //newsCategory类的vo构造器
    public ResultVo(NewsCategoryStateEnum newsCategoryStateEnum){
        this.state = newsCategoryStateEnum.getState();
        this.msg = newsCategoryStateEnum.getMsg();
    }

    public ResultVo(NewsCategoryStateEnum newsCategoryStateEnum ,T data){
        this.state = newsCategoryStateEnum.getState();
        this.msg = newsCategoryStateEnum.getMsg();
        this.data = data;
    }

    /**
     * comment的vo构造器
     * @param commentStateEnum
     */
    public ResultVo(CommentStateEnum commentStateEnum){
        this.state = commentStateEnum.getState();
        this.msg = commentStateEnum.getMsg();
    }

    public ResultVo(CommentStateEnum commentStateEnum, T data){
        this.state = commentStateEnum.getState();
        this.msg = commentStateEnum.getMsg();
        this.data = data;
    }

    /**
     * news
     * @param newsStateEnum
     */
    public ResultVo(NewsStateEnum newsStateEnum){
        this.state = newsStateEnum.getState();
        this.msg = newsStateEnum.getMsg();
    }

    public ResultVo(NewsStateEnum newsStateEnum,T data){
        this.state = newsStateEnum.getState();
        this.msg = newsStateEnum.getMsg();
        this.data = data;
    }

    /**
     * ConsultCategory
     * @param consultCategoryStateEnum
     */
    public ResultVo(ConsultCategoryStateEnum consultCategoryStateEnum){
        this.state = consultCategoryStateEnum.getState();
        this.msg = consultCategoryStateEnum.getMsg();
    }

    public ResultVo(ConsultCategoryStateEnum consultCategoryStateEnum, T data){
        this.state = consultCategoryStateEnum.getState();
        this.msg = consultCategoryStateEnum.getMsg();
        this.data = data;
    }

    /**
     * consult
     * @param consultStateEnum
     */
    public ResultVo(ConsultStateEnum consultStateEnum){
        this.state = consultStateEnum.getState();
        this.msg = consultStateEnum.getMsg();
    }

    public ResultVo(ConsultStateEnum consultStateEnum, T data){
        this.state = consultStateEnum.getState();
        this.msg = consultStateEnum.getMsg();
        this.data = data;
    }
    //....
}
