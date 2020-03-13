package com.jluzh.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author: yanghongkun
 * @description: 卖家信息表
 * @date: 2020/01/28
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private  String sellerId;

    private String username;

    private String password;

    private String openid;

//    private Date createTime;
//
//    private Date updateTime;
}
