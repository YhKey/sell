package com.jluzh.sell.controller;

import com.jluzh.sell.dataobject.ProductCategory;
import com.jluzh.sell.dataobject.ProductCategorySec;
import com.jluzh.sell.exception.SellException;
import com.jluzh.sell.form.CategoryForm;
import com.jluzh.sell.service.ProductCategoryService;
import com.jluzh.sell.utils.ResultVOUtil;
import com.jluzh.sell.vo.CategoryVO;
import com.jluzh.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author: yanghongkun
 * @description: 卖家类目
 * @date: 2020/01/26
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    /**
     * 类目列表
     *
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 展示类目详情信息
     *
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            /*ProductCategory productCategory = categoryService.findone(categoryId);
            map.put("productCategory", productCategory);*/
            CategoryVO productCategory = categoryService.findCategory(categoryId);
            map.put("productCategory", productCategory);
            map.put("productCategorySecList", productCategory.getProductCategorySecList());
        }
        return new ModelAndView("category/index", map);
    }

    /**
     * 展示类目详情信息
     *
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            List<ProductCategorySec> categorySecList = categoryService.findCategorySec(categoryId);
            map.put("categorySecList", categorySecList);
        }
        return new ModelAndView("category/index", map);
    }

    /**
     * 保存/更新
     * @Valid CategoryForm form,
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ResultVO save(@RequestBody @Valid CategoryForm form,
                         BindingResult bindingResult,
                         Map<String, Object> map) {
            if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
//            return new ModelAndView("common/error", map);
        }
        ProductCategory productCategory = new ProductCategory();
        try {
            categoryService.saveAll(form);
            map.put("success", true);
           /* if (form.getCategoryId() != null) {
                productCategory = categoryService.findone(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, productCategory);
            categoryService.save(productCategory);*/
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
//            return new ModelAndView("common/error", map);
        }catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
//            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/category/list");
//        return new ModelAndView("common/success", map);
        return ResultVOUtil.success();
    }
}
