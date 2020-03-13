package com.jluzh.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author: yanghongkun
 * @description: 买家详情信息
 * @date: 2020/02/13
 */
@Data
@DynamicUpdate
@Entity
public class BuyerDetail {

    @Id
    private int detailId;

    private int buyerId;

    private String name;

    private String phone;

    private String address;

    private Date createTime;

    private Date updateTime;

}
