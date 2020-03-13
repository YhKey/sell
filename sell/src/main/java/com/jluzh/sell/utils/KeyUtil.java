package com.jluzh.sell.utils;

import java.util.Random;

/**
 * @author: hongkun.yang
 * @description: 索引
 * @date: 2019/12/04
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     */
    public static String genUniqueKey() {
        //当前毫秒数
        long millis = System.currentTimeMillis();

        Random random = new Random();
        //生成两位随机数：Integer i = random.nextInt(90) + 10;
        //生成六位随机数
        Integer number = random.nextInt(900000) + 100000;

        return millis+String.valueOf(number);
    }
}
