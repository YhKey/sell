package com.jluzh.sell.repository;

import com.jluzh.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    
    /**
     * create by: hongkun.yang
     * description: 根据状态查询商品
     * create time: 2019/11/30
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /*
     *  废弃
     */
    Page<ProductInfo> findByCategoryTypeAndCategorySecTypeAndProductStatus(Integer categoryType,Integer categorySecType,Integer productStatus, Pageable pageable);
}
