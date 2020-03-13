package com.jluzh.sell.controller;

import com.jluzh.sell.dataobject.ProductCategory;
import com.jluzh.sell.dataobject.ProductCategorySec;
import com.jluzh.sell.dataobject.ProductInfo;
import com.jluzh.sell.exception.SellException;
import com.jluzh.sell.form.ProductFrom;
import com.jluzh.sell.service.ProductCategoryService;
import com.jluzh.sell.service.ProductService;
import com.jluzh.sell.utils.KeyUtil;
import com.jluzh.sell.utils.ResultVOUtil;
import com.jluzh.sell.vo.CategoryVO;
import com.jluzh.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author: yanghongkun
 * @description: 卖家端商品
 * @date: 2020/01/25
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService categoryService;

    /**
     *
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "bsize", defaultValue = "5") Integer size,
                             Map<String, Object> map){
        //定义的接口从第一页开始，查到相同数据page-1
        PageRequest pageRequest =  PageRequest.of(page - 1, size);
//        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try {
            productService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg","商品上架成功");
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try {
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg","商品下架成功");
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 新增和修改商品页面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                      Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findeone(productId);
            map.put("productInfo",productInfo);
        }

        //查询所有一级类目
//        List<ProductCategory> categoryList = categoryService.findAll();
        List<CategoryVO> categoryList = categoryService.findAllCategoryVO();
        map.put("categoryList",categoryList);

        return new ModelAndView("product/index",map);
    }

/*    @GetMapping("/detail")
    public ResultVO detail(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                               Map<String, Object> map) {
        if (categoryId != null) {
            List<ProductCategorySec> categorySecList = categoryService.findCategorySec(categoryId);
            map.put("categorySecList", categorySecList);
        }
//        List<CategoryVO> categoryList = categoryService.findAllCategoryVO();

        return ResultVOUtil.success(map);
    }*/

    /**
     * 保存/更新商品
     * @param from
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("save")
//    @CachePut(cacheNames = "product",key = "123")
    //访问方法之后，清除redis
    @CacheEvict(cacheNames = "product",key = "123")
    public ModelAndView save(@Valid ProductFrom from,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        try {
            ProductInfo productInfo = new ProductInfo();
            //productId不为空则为更新
            if (!StringUtils.isEmpty(from.getProductId())){
                productInfo = productService.findeone(from.getProductId());
            }else {
                from.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(from,productInfo);
            productService.save(productInfo);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

}
