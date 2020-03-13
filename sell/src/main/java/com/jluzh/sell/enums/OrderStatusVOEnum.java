package com.jluzh.sell.enums;

import lombok.Getter;

/**
 * @author: yanghongkun
 * @description:
 * @date: 2020/02/15
 */
@Getter
public enum  OrderStatusVOEnum implements CodeEnum{

    NEW(0,"待发货"),
    FINISHED(1,"待收货"),
    CANCEL(2,"已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusVOEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
