package com.jluzh.sell.repository;

import com.jluzh.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
* @author: yanghongkun
* @description:
* @date: 2020/1/28
*/
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);

    SellerInfo findByUsernameAndPassword(String username,String password);

}
