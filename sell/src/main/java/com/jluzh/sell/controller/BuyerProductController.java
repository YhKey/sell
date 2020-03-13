package com.jluzh.sell.controller;

import com.jluzh.sell.dataobject.ProductCategory;
import com.jluzh.sell.dataobject.ProductInfo;
import com.jluzh.sell.service.ProductCategoryService;
import com.jluzh.sell.service.ProductService;
import com.jluzh.sell.utils.ResultVOUtil;
import com.jluzh.sell.vo.OrderMasterVO;
import com.jluzh.sell.vo.ProductInfoVO;
import com.jluzh.sell.vo.ProductVO;
import com.jluzh.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author by: hongkun.yang
 * @description: 买家商品
 * @date: 2019/11/30
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
//    @Cacheable(cacheNames = "product",key = "#sellerId",condition = "#sellerId.length()>3",unless = "#result.getCode()!=0")
    public ResultVO<Map<String, Object>> list(@RequestParam(value = "categoryId") Integer categoryId,
                                              @RequestParam(value = "categorySecType") Integer categorySecType,
                                              @RequestParam(value = "page",defaultValue = "0") Integer page,
                                              @RequestParam(value = "size",defaultValue = "10") Integer size){
        Map<String, Object> map=new HashMap<>();
        PageRequest pageable= PageRequest.of(page,size);
        List<ProductInfoVO>  productList= productService.findList(pageable,categoryId,categorySecType);
        map.put("productList",productList);
        return ResultVOUtil.success(map);
    }

    @GetMapping("/detail")
    public ResultVO<Map<String, Object>> detail(@RequestParam(value = "productId") String productId,
                              Map<String,Object> map) {

        ProductInfo productInfo = productService.findeone(productId);
        ProductInfoVO productInfoVO=new ProductInfoVO();
        //将第一个bean的属性赋值到第二个bean
        BeanUtils.copyProperties(productInfo,productInfoVO);
        map.put("productInfo", productInfoVO);
        return ResultVOUtil.success(map);
    }
}
