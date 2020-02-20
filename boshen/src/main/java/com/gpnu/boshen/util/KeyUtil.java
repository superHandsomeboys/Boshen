package com.gpnu.boshen.util;


import java.util.Random;

public class KeyUtil {
    /**
     * 生成唯一的值
     * 时间+6位的随机数
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number =random.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
