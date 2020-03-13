package com.jluzh.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author by: hongkun.yang
 * @description: 商品详情
 * @date: 2019/11/30 20:12
 */
@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = 105510165884355696L;

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;


}
