package com.jluzh.sell.service;

import com.jluzh.sell.dataobject.SellerInfo;
import com.jluzh.sell.form.UserInfoForm;
import org.springframework.stereotype.Service;

/**
* @author: yanghongkun
* @description: 卖家端
* @date: 2020/1/29
*/
public interface SellerService {

    /**
     * create by: yanghongkun
     * description: 通过openid查询卖家端信息
     * create time: 2020/1/29
     * @param openid
     * @return
     */
    SellerInfo findBySellerInfoByOpenid(String openid);

    /**
     * 根据用户名、密码查询卖家端信息
     * @param form
     * @return
     */
    SellerInfo findBySellerInfoByUsernameAndPassword(UserInfoForm form);

}
