package com.jluzh.sell.dto;

import lombok.Data;

/**
 * @author: hongkun.yang
 * @description: 购物车
 * @date: 2019/12/04
 */
@Data
public class CartDTO {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
