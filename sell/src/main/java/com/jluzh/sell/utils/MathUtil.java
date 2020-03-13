package com.jluzh.sell.utils;

/**
 * @author: yanghongkun
 * @description:
 * @date: 2020/01/23
 */
public class MathUtil {

    public static final Double MONEY_RENGE = 0.01;

    /**
     * create by: yanghongkun
     * description: 比较两个金额是否相等
     * create time: 2020/1/23
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {
        Double result = d1 - d2;
        if (result < MONEY_RENGE) {
            //两边金额一致
            return true;
        } else {
            return false;
        }

    }
}
