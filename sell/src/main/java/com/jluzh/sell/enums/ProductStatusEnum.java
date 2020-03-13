package com.jluzh.sell.enums;

import lombok.Getter;

/**
 * @author by: hongkun.yang
 * @description: 商品状态
 * @date: 2019/11/30 13:51
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{

    UP(0,"在架"),
    DOWN(1,"下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
