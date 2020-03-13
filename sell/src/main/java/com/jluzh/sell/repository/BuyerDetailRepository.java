package com.jluzh.sell.repository;

import com.jluzh.sell.dataobject.BuyerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyerDetailRepository extends JpaRepository<BuyerDetail,String> {

    BuyerDetail findByBuyerId(int buyerId);

    List<BuyerDetail> findAllByBuyerId(int buyerId);


}
