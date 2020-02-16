package com.gpnu.boshen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//轮播图类
@NoArgsConstructor//无参构造函数
@Data//getter、setter、toString
@Accessors(chain = true)//setter方法返回当前对象
public class Slider implements Serializable {

    private Integer id;
    private String name;
    //type为0代表关于我们页，为1代表首页
    private int type;
    private String uri;
}
