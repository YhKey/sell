package com.jluzh.sell.dataobject.mapper;

import com.jluzh.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception{
        Map<String,Object> map=new HashMap<>();
        map.put("categoryName","女神");
        map.put("categoryType",30);
        int i = mapper.insertByMap(map);
        Assert.assertEquals(1,i);
    }

    @Test
    public void insertByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女神");
        productCategory.setCategoryType(40);
        int i = mapper.insertByObject(productCategory);
        Assert.assertEquals(1,i);
    }

    @Test
    public void findBycategoryType(){
        ProductCategory productCategory = mapper.findByCategoryType(30);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findBycategoryName(){
        List<ProductCategory> result = mapper.findByCategoryName("女神");
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void updateBycategoryType(){
        int i = mapper.updateByCategoryType("女神最爱", 30);
        Assert.assertEquals(1,i);
    }

    @Test
    public void updateByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女神");
        productCategory.setCategoryType(30);
        int i = mapper.updateByObject(productCategory);
        Assert.assertEquals(1,i);
    }

    @Test
    public void delectByCategoryType(){
        int i = mapper.deleteByCategoryType(40);
        Assert.assertEquals(1,i);
    }

    @Test
    public void select(){
        ProductCategory productCategory = mapper.selectByCategoryType(30);
        Assert.assertNotNull(productCategory);
    }
}