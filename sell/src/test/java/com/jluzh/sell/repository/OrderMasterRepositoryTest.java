package com.jluzh.sell.repository;

import com.jluzh.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID="1008";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("2");
        orderMaster.setBuyerName("key");
        orderMaster.setBuyerPhone("13726262815");
        orderMaster.setBuyerAddress("GF");
        orderMaster.setBuyerOpenId(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(840));

        OrderMaster result = repository.save(orderMaster);
        //不为null表示成功
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenId() {
        PageRequest pageRequest =  PageRequest.of(0, 2);
        Page<OrderMaster> orderMasters = repository.findBybuyerOpenId(OPENID, pageRequest);
        //对应条数不等于0
        Assert.assertNotEquals(0,orderMasters.getTotalElements());
        System.out.println(orderMasters.getTotalElements());
        System.out.println(orderMasters.getTotalPages());
    }
}