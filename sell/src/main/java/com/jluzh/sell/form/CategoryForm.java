package com.jluzh.sell.form;

import com.jluzh.sell.dataobject.ProductCategorySec;
import lombok.Data;

import java.util.List;

/**
 * @author: yanghongkun
 * @description: 类目表单对象
 * @date: 2020/01/25
 */
@Data
public class CategoryForm {
    private Integer categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;

    List<ProductCategorySec> productCategorySecList;
}
