package com.jluzh.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author: yanghongkun
 * @description: 买家信息
 * @date: 2020/02/12
 */
@Data
@DynamicUpdate
@Entity
public class BuyerInfo {

    @Id
    private int buyerId;

    private String  openid;

    private Date createTime;

    private Date updateTime;

}
