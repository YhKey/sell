package com.jluzh.sell.constant;

/**
* @author: yanghongkun
* @description: redis常量
* @date: 2020/1/31
*/
public interface RedisConstant {

    /**
     * 前缀
     */
    String TOKEN_PREFIX="token_%s";

    /**
     * 过期时间,2小时
     */
    Integer EXPIRE =7200;
}
