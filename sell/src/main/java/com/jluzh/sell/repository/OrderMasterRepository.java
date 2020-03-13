package com.jluzh.sell.repository;

import com.jluzh.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

   /**
    * create by: hongkun.yang
    * description: 根据买家的openId，分页查询订单
    * create time: 2019/12/4
    * @param buyerOpenId
	 * @param pageable
    * @return
    */
    Page<OrderMaster> findBybuyerOpenId(String buyerOpenId, Pageable pageable);

    
}
