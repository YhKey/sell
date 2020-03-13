package com.jluzh.sell.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * @author: yanghongkun
 * @description: 买家详情信息
 * @date: 2020/02/13
 */
@Data
public class BuyerDetailVO implements Serializable {

    private int detailId;

    private String name;

    private String phone;

    private String address;

}
