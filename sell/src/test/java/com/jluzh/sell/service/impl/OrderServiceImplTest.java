package com.jluzh.sell.service.impl;

import com.jluzh.sell.dataobject.OrderDetail;
import com.jluzh.sell.dto.OrderDTO;
import com.jluzh.sell.enums.OrderStatusEnum;
import com.jluzh.sell.enums.PayStatusEnum;
import com.jluzh.sell.repository.OrderMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private final String BUYER_OPEN_ID="971008";

    private final String ORDER_ID="1578739276523245156";

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() throws Exception{
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("yhk");
        orderDTO.setBuyerPhone("13726262815");
        orderDTO.setBuyerAddress("BHJC");
        orderDTO.setBuyerOpenId(BUYER_OPEN_ID);

        //购物车
        List<OrderDetail> detailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("003");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("001");
        o2.setProductQuantity(2);

        detailList.add(o1);
        detailList.add(o2);

        orderDTO.setOrderDetailList(detailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result={}"+result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findone() throws Exception{
        OrderDTO result = orderService.findone(ORDER_ID);
        log.info("【查询单个订单】result={}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request= PageRequest.of(0,2);
//        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPEN_ID, request);
//        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findone(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findone(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception{
        OrderDTO orderDTO = orderService.findone(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

    @Test
    public void list(){
        PageRequest request= PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
//        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
        Assert.assertTrue("查询所有订单列表",orderDTOPage.getTotalElements()>0);
    }

}