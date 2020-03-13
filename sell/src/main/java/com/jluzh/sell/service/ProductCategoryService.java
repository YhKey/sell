package com.jluzh.sell.service;

import com.jluzh.sell.dataobject.ProductCategory;
import com.jluzh.sell.dataobject.ProductCategorySec;
import com.jluzh.sell.exception.SellException;
import com.jluzh.sell.form.CategoryForm;
import com.jluzh.sell.vo.CategoryVO;

import java.util.List;

public interface ProductCategoryService {
    /**
     * create by: hongkun.yang
     * description: 根据ID查询一条记录
     * create time: 2019/11/28
     * @param categoryId
     * @return
     */
   ProductCategory findone(Integer categoryId);

   /**
    * create by: hongkun.yang
    * description: 查询所有类目
    * create time: 2019/11/28
    * @param 
    * @return
    */
   List<ProductCategory> findAll();

    /**
     * 查询前端VO对象
     * @return
     */
    List<CategoryVO> findAllCategoryVO();

   /**
    * create by: hongkun.yang
    * description: 使用IN进行查询
    * create time: 2019/11/28
    * @param categoryTypeList
    * @return
    */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * create by: hongkun.yang
     * description: 新增和更新
     * create time: 2019/11/28
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);

    /**
     * create by: yanghongkun
     * description: 头行保存
     * create time: 2020/2/21
     * @return
     */
    void saveAll(CategoryForm form) throws Exception;

    /**
     * 根据categoryId查询二级类目
     */
    List<ProductCategorySec> findCategorySec(Integer categoryId);

    /**
     * 头行查询
     */
    CategoryVO findCategory(Integer categoryId);

}
