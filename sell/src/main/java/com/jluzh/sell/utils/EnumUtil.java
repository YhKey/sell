package com.jluzh.sell.utils;

import com.jluzh.sell.enums.CodeEnum;

/**
 * @author: yanghongkun
 * @description: 枚举工具类
 * @date: 2020/01/23
 */
public class EnumUtil {

    /** 对枚举T说明，继承CodeEnum */
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each: enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
