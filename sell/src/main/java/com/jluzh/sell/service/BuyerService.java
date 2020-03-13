package com.jluzh.sell.service;

import com.jluzh.sell.dataobject.BuyerDetail;
import com.jluzh.sell.dataobject.BuyerInfo;
import com.jluzh.sell.dto.OrderDTO;
import com.jluzh.sell.form.AddressForm;
import com.jluzh.sell.vo.BuyerDetailVO;

import java.util.List;

/**
 * @author: yanghongkun
 * @description: 买家service
 * @date: 2020/1/14
 */
public interface BuyerService {

    //根据openid查询买家用户信息
    BuyerInfo findBuyerOne(String openid);

    //创建买家用户信息
    BuyerInfo create(String openid);

    //根据buyerId查询明细
    BuyerDetail findBuyerDetailByBuyerId(int buyerId);

    //保存用户地址信息
    void saveBuyerDetailInfo(AddressForm addressForm);

    //根据openid查询用户详情信息
    List<BuyerDetailVO> findAllByOpenid(String openid);

    //删除地址信息
    boolean deleteDetail(Integer detailId);



    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);


}
