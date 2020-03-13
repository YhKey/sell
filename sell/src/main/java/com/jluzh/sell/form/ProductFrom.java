package com.jluzh.sell.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: yanghongkun
 * @description: 商品表单对象
 * @date: 2020/01/25
 */
@Data
public class ProductFrom {


    private String productId;

    /**
     * 名称
     */
    private String productName;

    /**
     * 单价
     */
    private BigDecimal productPrice;

    /**
     *  库存
     */
    private Integer productStock;

    /**
     * 描述
     */
    private String productDescription;

    /**
     * 小图连接
     */
    private  String productIcon;

    /**
     * 一级类目编号
     */
    private Integer categoryType;

    /**
     * 一级类目编号
     */
    private Integer categorySecType;
}
