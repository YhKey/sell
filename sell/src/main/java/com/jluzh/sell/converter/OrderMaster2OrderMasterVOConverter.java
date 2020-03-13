package com.jluzh.sell.converter;

import com.jluzh.sell.dataobject.OrderMaster;
import com.jluzh.sell.dto.OrderDTO;
import com.jluzh.sell.enums.OrderStatusEnum;
import com.jluzh.sell.enums.OrderStatusVOEnum;
import com.jluzh.sell.utils.EnumUtil;
import com.jluzh.sell.vo.OrderMasterVO;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.EmitUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yanghongkun
 * @description:
 * @date: 2020/02/15
 */
public class OrderMaster2OrderMasterVOConverter {

    public static OrderMasterVO convert(OrderMaster orderMaster){
        OrderMasterVO orderMasterVO = new OrderMasterVO();
        BeanUtils.copyProperties(orderMaster,orderMasterVO);
        EnumUtil.getByCode(orderMaster.getOrderStatus(), OrderStatusVOEnum.class);
        orderMasterVO.setOrderStatusMsg(EnumUtil.getByCode(orderMaster.getOrderStatus(), OrderStatusVOEnum.class).getMessage());
        return orderMasterVO;
    }

    public static List<OrderMasterVO> convert(List<OrderMaster> orderMasterList){
        return  orderMasterList.stream().map(e->
                convert(e)
        ).collect(Collectors.toList());
    }
}
