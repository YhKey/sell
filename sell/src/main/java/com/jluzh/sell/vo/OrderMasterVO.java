package com.jluzh.sell.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jluzh.sell.dataobject.OrderDetail;
import com.jluzh.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: yanghongkun
 * @description: 买家端订单头
 * @date: 2020/02/15
 */
@Data
public class OrderMasterVO implements Serializable {
    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家手机
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单状态msg
     */
    private String orderStatusMsg;

    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 订单创建时间，按时间排序
     */
//    @JsonSerialize(using = Date2LongSerializer.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    List<OrderDetail> orderDetailList;
}
