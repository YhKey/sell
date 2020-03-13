package com.jluzh.sell.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * @author: yanghongkun
 * @description:
 * @date: 2020/02/03
 */
@Data
public class Area {

    private Integer areaId;

    private String areaName;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

}
