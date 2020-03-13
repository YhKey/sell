package com.jluzh.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jluzh.sell.dataobject.OrderDetail;
import com.jluzh.sell.dto.OrderDTO;
import com.jluzh.sell.enums.ResultEnum;
import com.jluzh.sell.exception.SellException;
import com.jluzh.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
* @author: yanghongkun
* @description:
* @date: 2020/1/14
*/
@Slf4j
public class OrderForm2OrderDtoConverter {
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson=new Gson();

        //不能使用beanUtils转换，属性名不同
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenId(orderForm.getOpenId());
        //items为json格式，转换
        List<OrderDetail> orderDetailList=new ArrayList<>();
        try {
            orderDetailList=gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
