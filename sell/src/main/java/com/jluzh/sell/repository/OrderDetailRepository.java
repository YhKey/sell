package com.jluzh.sell.repository;

import com.jluzh.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * create by: hongkun.yang
     * description: 根据订单ID查询订单明细信息（一对多）
     * create time: 2019/12/4
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
