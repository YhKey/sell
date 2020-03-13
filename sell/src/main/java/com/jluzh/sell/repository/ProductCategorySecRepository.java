package com.jluzh.sell.repository;

import com.jluzh.sell.dataobject.ProductCategorySec;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductCategorySecRepository extends JpaRepository<ProductCategorySec,Integer> {

    List<ProductCategorySec> findAllByCategoryId(Integer categoryId);

    ProductCategorySec findByCategoryIdAndCategorySecType(Integer categoryId,Integer categorySecType);
}
