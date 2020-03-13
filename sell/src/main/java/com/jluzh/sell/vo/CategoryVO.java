package com.jluzh.sell.vo;

import com.jluzh.sell.dataobject.ProductCategorySec;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: yanghongkun
 * @description: 类目
 * @date: 2020/02/21
 */
@Data
public class CategoryVO implements Serializable {

    private Integer categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    List<ProductCategorySec> productCategorySecList;

}
