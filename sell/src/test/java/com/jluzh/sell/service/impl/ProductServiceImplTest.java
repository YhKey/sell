package com.jluzh.sell.service.impl;

import com.jluzh.sell.dataobject.ProductInfo;
import com.jluzh.sell.enums.ProductStatusEnum;
import com.jluzh.sell.vo.ProductInfoVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl service;

    @Test
    public void findeone() {
        ProductInfo findeone = service.findeone("001");
        Assert.assertEquals("001",findeone.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = service.findUpAll();
        Assert.assertNotEquals("0",upAll.size());
    }

    @Test
    public void findAll() {
        //第几页，有几条内容
        PageRequest pageRequest= PageRequest.of(0,2);
//        Page<ProductInfo> all = service.findAll(pageRequest);
//        System.out.println(all.getTotalElements());
//        Assert.assertNotEquals(0,all.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("003");
        productInfo.setProductName("SpuerTouFu");
        productInfo.setProductPrice(new BigDecimal(229));
        productInfo.setProductStock(50);
        productInfo.setProductDescription("黑色军裤");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setCategoryType(5);
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        ProductInfo save = service.save(productInfo);
        Assert.assertNotNull(save);
    }

    @Test
    public void onSale(){
        ProductInfo result = service.onSale("003");
        //期待值，实际值
        Assert.assertEquals(ProductStatusEnum.UP,result.getProductStatusEnum());
    }
    @Test
    public void offSale(){
        ProductInfo result = service.offSale("003");
        //期待值，实际值
        Assert.assertEquals(ProductStatusEnum.DOWN,result.getProductStatusEnum());
    }

    @Test
    public void findProduct(){
        /*PageRequest request= PageRequest.of(0,10);
        Page<ProductInfoVO> list = service.findList(request, 3, 10);
        System.out.println("【Page<ProductInfoVO>】："+list);*/
    }
}