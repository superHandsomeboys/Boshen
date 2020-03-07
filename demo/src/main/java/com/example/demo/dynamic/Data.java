package com.example.demo.dynamic;

/**
 * 存放代码里的动态数据
 */
public class Data {
    //上传图片的路径
//    public static String UPLOAD_IMAGE_PATH = "/usr/local/images/";
    public static String UPLOAD_IMAGE_PATH = "C:\\Users\\Administrator\\Desktop\\images\\";

    //系统文档路径的分隔符,目前是windows，linux是"/"
    public static String SEPARATOR = "\\";
//    public static String SEPARATOR = "/";

    //存放系统文件的系统文件夹名
    public static String SYSTEM_FOLDER = "system";

    //用户默认头像在images/system/
    public static String DEFAULT_AVATAR = "system/defaultAvatar.png";

    //分页查询的每页的内容数量 8
    public static int QUANTITY_IN_PAGE= 8;
}
