package com.jluzh.sell.service.impl;

import com.jluzh.sell.dataobject.BuyerDetail;
import com.jluzh.sell.dataobject.BuyerInfo;
import com.jluzh.sell.dto.OrderDTO;
import com.jluzh.sell.enums.ResultEnum;
import com.jluzh.sell.exception.SellException;
import com.jluzh.sell.form.AddressForm;
import com.jluzh.sell.repository.BuyerDetailRepository;
import com.jluzh.sell.repository.BuyerInfoRepository;
import com.jluzh.sell.service.BuyerService;
import com.jluzh.sell.service.OrderService;
import com.jluzh.sell.utils.ResultVOUtil;
import com.jluzh.sell.vo.BuyerDetailVO;
import com.jluzh.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yanghongkun
 * @description:
 * @date: 2020/01/14
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerInfoRepository buyerInfoRepository;

    @Autowired
    private BuyerDetailRepository buyerDetailRepository;

    @Override
    public BuyerInfo findBuyerOne(String openid) {
        BuyerInfo buyerInfo = buyerInfoRepository.findByOpenid(openid);
        return buyerInfo;
    }

    @Override
    public BuyerInfo create(String openid) {
        BuyerInfo buyerInfo = new BuyerInfo();
        buyerInfo.setOpenid(openid);
        try {
            buyerInfo = buyerInfoRepository.save(buyerInfo);
        } catch (SellException e) {
            log.error("【新增用戶信息】失败，openid={}", openid);
            throw new SellException(ResultEnum.CREATE_BUYER_INFO_FAIL);
        }
        return buyerInfo;
    }

    @Override
    public BuyerDetail findBuyerDetailByBuyerId(int buyerId) {
        return buyerDetailRepository.findByBuyerId(buyerId);
    }

    @Override
    public void saveBuyerDetailInfo(AddressForm addressForm) {
        BuyerInfo buyer = this.findBuyerOne(addressForm.getOpenid());
        if (buyer != null) {
            BuyerDetail buyerDetail =new BuyerDetail();
            BeanUtils.copyProperties(addressForm,buyerDetail);
            buyerDetail.setBuyerId(buyer.getBuyerId());
            buyerDetailRepository.save(buyerDetail);
        }else{
            log.error("【保存用户地址信息】查不到该订单，openid={}", addressForm.getOpenid());
            throw new SellException(ResultEnum.BUYER_NOT_EXIST);
        }
    }

    @Override
    public List<BuyerDetailVO> findAllByOpenid(String openid) {
        BuyerInfo buyer = this.findBuyerOne(openid);
        List<BuyerDetail> buyerDetailList = buyerDetailRepository.findAllByBuyerId(buyer.getBuyerId());
        List<BuyerDetailVO> buyerDetailVOList=new ArrayList<>();
        for (BuyerDetail buyerDetail:buyerDetailList){
            BuyerDetailVO buyerDetailVO=new BuyerDetailVO();
            BeanUtils.copyProperties(buyerDetail,buyerDetailVO);
            buyerDetailVOList.add(buyerDetailVO);
        }
        return buyerDetailVOList;
    }

    @Override
    public boolean deleteDetail(Integer detailId) {
        BuyerDetail buyerDetail = new BuyerDetail();
        buyerDetail.setDetailId(detailId);
        try {
            buyerDetailRepository.delete(buyerDetail);
        }catch (RuntimeException e){
            log.error("【删除地址信息失败】，detailId={}", detailId);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到该订单，orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findone(orderId);
        //查不到订单
        if (orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenId().equals(openid)) {
            log.error("【查询订单】订单的openid不一致，openid={},orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
