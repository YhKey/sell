package com.jluzh.sell.repository;

import com.jluzh.sell.dataobject.BuyerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerInfoRepository extends JpaRepository<BuyerInfo,String> {

    BuyerInfo findByOpenid(String openid);
}
