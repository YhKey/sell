package com.jluzh.sell.service.impl;

import com.jluzh.sell.dataobject.ProductCategory;
import com.jluzh.sell.dataobject.ProductCategorySec;
import com.jluzh.sell.dataobject.ProductInfo;
import com.jluzh.sell.dto.CartDTO;
import com.jluzh.sell.enums.ProductStatusEnum;
import com.jluzh.sell.enums.ResultEnum;
import com.jluzh.sell.exception.SellException;
import com.jluzh.sell.repository.ProductCategoryRepository;
import com.jluzh.sell.repository.ProductCategorySecRepository;
import com.jluzh.sell.repository.ProductInfoRepository;
import com.jluzh.sell.service.ProductService;
import com.jluzh.sell.vo.ProductInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author by: hongkun.yang
 * @description: 商品信息
 * @date: 2019/11/30 12:27
 */
@Service
//@CacheConfig(cacheNames = "ProductInfo")
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private ProductCategorySecRepository categorySecRepository;

    @Override
    //key不填，则key的默认参数为方法的参数
//    @Cacheable(key = "123")
    public ProductInfo findeone(String productId) {
        return repository.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        Page<ProductInfo> all = repository.findAll(pageable);
        for (ProductInfo productInfo: all){
            ProductCategory category = categoryRepository.findByCategoryType(productInfo.getCategoryType());
            productInfo.setCategoryName(category.getCategoryName());
            if (productInfo.getCategorySecType()!=null){
                ProductCategorySec secType = categorySecRepository.findByCategoryIdAndCategorySecType(category.getCategoryId(), productInfo.getCategorySecType());
                productInfo.setCategorySecName(secType.getCategorySecName());
            }else {
                productInfo.setCategorySecName("1");
            }
        }
        return all;
    }

    @Override
//    @CachePut(key = "123")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional(rollbackFor = SellException.class)
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            //判断是否小于0，库存不足
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).orElse(null);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).orElse(null);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }

    @Override
    public List<ProductInfoVO> findList(Pageable pageable, Integer categoryId, Integer categorySecType) {
        //1.查询所有上架商品
//        List<ProductInfo> productInfoList = this.findUpAll();
        //2.查詢类目
        ProductCategory category = categoryRepository.findById(categoryId).orElse(null);
        Page<ProductInfo> infoPage = repository.findByCategoryTypeAndCategorySecTypeAndProductStatus(category.getCategoryType(), categorySecType, ProductStatusEnum.UP.getCode(), pageable);

        List<ProductInfoVO> productInfoVOList=new ArrayList<>();
        /*List<ProductInfo> infoList = productInfoList.stream().filter(e ->
                e.getCategoryType().equals(category.getCategoryType()) &&
                        e.getCategorySecType().equals(categorySecType)
        ).collect(Collectors.toList());*/
        for (ProductInfo productInfo : infoPage){
            ProductInfoVO vo=new ProductInfoVO();
            BeanUtils.copyProperties(productInfo,vo);
            productInfoVOList.add(vo);
        }
        return productInfoVOList;
    }
}
