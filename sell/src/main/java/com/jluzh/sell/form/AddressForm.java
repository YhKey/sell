package com.jluzh.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: yanghongkun
 * @description: 买家地址表单
 * @date: 2020/02/13
 */
@Data
public class AddressForm {
    /**
     * 买家openId
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 买家收件人姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    private String address;

}
