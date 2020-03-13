package com.jluzh.sell.dataobject;

import com.jluzh.sell.enums.OrderStatusEnum;
import com.jluzh.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author by: hongkun.yang
 * @description: 订单头实体
 * @date: 2019/12/04
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /**
     * 订单ID
     */
    @Id
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
     * 买家微信OpenID
     */
    private String buyerOpenId;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态,默认为新下单0，
     */
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态，默认未支付0，
     */
    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    /**
     * 订单创建时间，按时间排序
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public OrderMaster() {
    }
}
