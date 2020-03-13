package com.jluzh.sell.controller;

import com.jluzh.sell.dataobject.ProductCategory;
import com.jluzh.sell.exception.SellException;
import com.jluzh.sell.form.CategoryForm;
import com.jluzh.sell.service.ProductCategoryService;
import com.jluzh.sell.utils.ResultVOUtil;
import com.jluzh.sell.vo.CategoryVO;
import com.jluzh.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yanghongkun
 * @description: 卖家类目
 * @date: 2020/01/26
 */
@RestController
@RequestMapping("/buyer/category")
public class BuyerCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    /**
     * 类目列表
     *
     * @return
     */
/*    @GetMapping("/list")
    public Map<String,Object> list() {
        List<ProductCategory> categoryList = categoryService.findAll();
        Map<String, Object> map=new HashMap<>();
        map.put("categoryList", categoryList);
        return map;
    }*/
    @GetMapping("/list")
    public ResultVO<Map<String, Object>> list() {
//        List<ProductCategory> categoryList = categoryService.findAll();
        List<CategoryVO> categoryVOList = categoryService.findAllCategoryVO();
        Map<String, Object> map=new HashMap<>();
        map.put("categoryList", categoryVOList);
        return ResultVOUtil.success(map);
    }

/*    *//**
     * 展示类目详情信息
     *
     * @param categoryId
     * @param map
     * @return
     *//*
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory productCategory = categoryService.findone(categoryId);
            map.put("productCategory", productCategory);
            *//*CategoryVO productCategory = categoryService.findCategory(categoryId);
            map.put("productCategory", productCategory);*//*
        }
        return new ModelAndView("category/index", map);
    }*/

}
