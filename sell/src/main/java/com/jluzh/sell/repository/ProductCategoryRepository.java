package com.jluzh.sell.repository;

import com.jluzh.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    /**
     * create by: hongkun.yang
     * description: 使用IN进行查询
     * create time: 2019/11/28
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);


    ProductCategory findByCategoryType(Integer categoryType);
}
