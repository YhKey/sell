package com.jluzh.sell.repository;

import com.jluzh.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        ProductCategory one = repository.findById(1).orElse(null);
        System.out.println(one.toString());
    }

    @Test
    @Transactional
    public void saveTest(){
        ProductCategory p=new ProductCategory("男生最爱",20);
        ProductCategory result = repository.save(p);
        //断言,返回的值不为空
        Assert.assertNotNull(result);
        //不期望为NULL，实际的值
//        Assert.assertNotEquals(null,result);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> integers= Arrays.asList(2,10,5);
        List<ProductCategory> result = repository.findByCategoryTypeIn(integers);
        Assert.assertNotEquals("0",result.size());
    }
}