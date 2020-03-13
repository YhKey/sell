package com.jluzh.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author by: hongkun.yang
 * @description: 商品（包含类目）
 * @date: 2019/11/30
 */
@Data
public class ProductVO implements Serializable {


    private static final long serialVersionUID = 2841138726605362310L;
    @JsonProperty("categoryName")
    private String categoryName;

    @JsonProperty("categoryType")
    private Integer categoryType;

    @JsonProperty("products")
    private List<ProductInfoVO> productInfoVOList;

}
