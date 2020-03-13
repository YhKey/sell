package com.jluzh.sell.converter;

import com.jluzh.sell.dataobject.OrderMaster;
import com.jluzh.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yanghongkun
 * @description:
 * @date: 2020/01/11
 */
public class OrderMaster2OrderDtoConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return  orderMasterList.stream().map(e->
                    convert(e)
                ).collect(Collectors.toList());
    }
}
