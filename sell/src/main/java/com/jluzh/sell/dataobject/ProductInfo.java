package com.jluzh.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jluzh.sell.enums.ProductStatusEnum;
import com.jluzh.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.cglib.core.EmitUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author by: hongkun.yang
 * @description: 商品信息
 * @date: 2019/11/30 10:59
 */
@Entity
@Data
//时间字段自动更新
@DynamicUpdate
public class ProductInfo implements Serializable {

    @Id
    private String productId;

    /**
     * 名称
     */
    private String productName;

    /**
     * 单价
     */
    private BigDecimal productPrice;

    /**
     *  库存
     */
    private Integer productStock;

    /**
     * 描述
     */
    private String productDescription;

    /**
     * 小图连接,使用链接，支持分布式
     * （如果使用传统的图片上传方式，传到某台服务器的路径下，达不到分布式的目的）
     * 解决：使用第三方CDN存储，或者专门搭一台文件服务器
     */
    private  String productIcon;

    /**
     *  状态：0正常1下架
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /**
     * 一级类目编号
     */
    private Integer categoryType;

    /**
     * 一级类目编号
     */
    private Integer categorySecType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }

    /*add 类目名称*/
    @Transient
    private String categoryName;

    @Transient
    private String categorySecName;
    /*end*/
}



