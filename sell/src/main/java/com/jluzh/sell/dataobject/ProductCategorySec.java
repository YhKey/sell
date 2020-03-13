package com.jluzh.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
* @author: yanghongkun
* @description: 二级类目实体
* @date: 2020/2/20
*/
@Entity
@DynamicUpdate
@Data
@Table(name = "product_category_sec")
public class ProductCategorySec {

    /**
     * 二级类目ID
     */
    @Id
//    @GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categorySecId;


    /*
     *  一级类目ID
     */
    private Integer categoryId;
    /**
     * 二级类目名称
     */
    private String categorySecName;

    /**
     * 二级类目编号
     */
    private Integer categorySecType;

    private  String categoryIcon;

    private Date createTime;

    private Date updateTime;

    public ProductCategorySec(String categorySecName, Integer categorySecType) {
        this.categorySecName = categorySecName;
        this.categorySecType = categorySecType;
    }

    public ProductCategorySec() {
    }
}
