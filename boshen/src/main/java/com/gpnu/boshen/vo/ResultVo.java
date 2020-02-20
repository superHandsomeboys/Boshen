package com.gpnu.boshen.vo;
import com.gpnu.boshen.enums.CommentStateEnum;
import com.gpnu.boshen.enums.NewsCategoryStateEnum;
import com.gpnu.boshen.enums.NewsStateEnum;
import com.gpnu.boshen.enums.UserStateEnum;
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

    //newsCategory类的vo构造器
    public ResultVo(NewsCategoryStateEnum newsCategoryStateEnum){
        this.state = newsCategoryStateEnum.getState();
        this.msg = newsCategoryStateEnum.getMsg();
    }

    /**
     * comment的vo构造器
     * @param commentStateEnum
     */
    public ResultVo(CommentStateEnum commentStateEnum){
        this.state = commentStateEnum.getState();
        this.msg = commentStateEnum.getMsg();
    }

    public ResultVo(NewsStateEnum newsStateEnum){
        this.state = newsStateEnum.getState();
        this.msg = newsStateEnum.getMsg();
    }
    //....
}
