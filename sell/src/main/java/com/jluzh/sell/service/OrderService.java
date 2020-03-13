package com.jluzh.sell.service;

import com.jluzh.sell.dataobject.OrderMaster;
import com.jluzh.sell.dto.OrderDTO;
import com.jluzh.sell.vo.OrderMasterVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.Order;

/**
 * @author: hongkun.yang
 * @description:
 * @date: 2019/12/4
 */
public interface OrderService {
    /**
     * 创建订单
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     */
    OrderDTO findone(String orderId);

    /**
     * 查询订单列表
     */
    Page<OrderMasterVO> findList(String buyerOpenId, Pageable pageable);

    /**
     * 取消订单
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     * 查询订单列表
     */
    Page<OrderDTO> findList(Pageable pageable);
}
