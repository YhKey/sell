package com.jluzh.sell.service.impl;

import com.jluzh.sell.dataobject.ProductCategory;
import com.jluzh.sell.dataobject.ProductCategorySec;
import com.jluzh.sell.dataobject.mapper.ProductCategoryMapper;
import com.jluzh.sell.form.CategoryForm;
import com.jluzh.sell.repository.ProductCategoryRepository;
import com.jluzh.sell.repository.ProductCategorySecRepository;
import com.jluzh.sell.service.ProductCategoryService;
import com.jluzh.sell.vo.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by: hongkun.yang
 * @description: ProductCategoryService实现类
 * @date: 2019/11/28 16:34
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private ProductCategoryMapper mapper;

    @Autowired
    private ProductCategorySecRepository secRepository;

    @Override
    public ProductCategory findone(Integer categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CategoryVO> findAllCategoryVO() {
        List<CategoryVO> categoryVOList=new ArrayList<>();
        List<ProductCategory> categoryList = repository.findAll();
        for (ProductCategory productCategory:categoryList){
            CategoryVO categoryVO=new CategoryVO();
            BeanUtils.copyProperties(productCategory,categoryVO);
            List<ProductCategorySec> categorySecList = secRepository.findAllByCategoryId(categoryVO.getCategoryId());
            categoryVO.setProductCategorySecList(categorySecList);
            categoryVOList.add(categoryVO);
        }
        return categoryVOList;
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    public void saveAll(CategoryForm form) throws Exception {
        ProductCategory productCategory = new ProductCategory();
        List<ProductCategorySec> categorySecList = new ArrayList<>();
        try {
            if (form.getCategoryId() != null) {
                productCategory = this.findone(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, productCategory);
            productCategory = this.save(productCategory);

            categorySecList = form.getProductCategorySecList();
            for (ProductCategorySec categorySec : categorySecList) {
                categorySec.setCategoryId(productCategory.getCategoryId());
                ProductCategorySec productCategorySec=new ProductCategorySec();
                if (categorySec.getCategorySecId() != null){
                     productCategorySec = secRepository.findById(categorySec.getCategorySecId()).orElse(null);
                }
                BeanUtils.copyProperties(categorySec, productCategorySec);
                secRepository.save(productCategorySec);
            }
        } catch (Exception e) {
            log.info("【类别头行保存错误】：", e);
            throw e;
        }
    }

    @Override
    public List<ProductCategorySec> findCategorySec(Integer categoryId) {
        return secRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public CategoryVO findCategory(Integer categoryId) {
        ProductCategory productCategory = repository.findById(categoryId).orElse(null);
        CategoryVO categoryVO=new CategoryVO();
        BeanUtils.copyProperties(productCategory,categoryVO);
        List<ProductCategorySec> productCategorySecList = secRepository.findAllByCategoryId(categoryId);
        categoryVO.setProductCategorySecList(productCategorySecList);
        return categoryVO;
    }
}
