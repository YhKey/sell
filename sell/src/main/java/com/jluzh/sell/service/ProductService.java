package com.jluzh.sell.service;

import com.jluzh.sell.dataobject.ProductInfo;
import com.jluzh.sell.dto.CartDTO;
import com.jluzh.sell.vo.OrderMasterVO;
import com.jluzh.sell.vo.ProductInfoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    
    /**
     * create by: hongkun.yang
     * description: 查找具体商品
     * create time: 2019/11/30
     * @param productId
     * @return
     */
    ProductInfo findeone(String productId);

    /**
     * create by: hongkun.yang
     * description: 查询所有在架商品列表
     * create time: 2019/11/30
     * @param
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * create by: hongkun.yang
     * description: 分页查询
     * create time: 2019/11/30
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * create by: hongkun.yang
     * description: 新增或保存
     * create time: 2019/11/30
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);
    
    /**
     * create by: hongkun.yang
     * description: 加库存
     * create time: 2019/12/4
     * @param cartDTOList
     * @return
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * create by: hongkun.yang
     * description: 减库存
     * create time: 2019/12/4
     * @param cartDTOList
     * @return
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 商品上架
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 商品下架
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);

    /**
     * create by: yanghongkun
     * description: 根据类目查询所有在架商品
     * create time: 2020/2/25
     * @return
     */
    List<ProductInfoVO> findList(Pageable pageable,Integer categoryId,Integer categorySecType);
}
