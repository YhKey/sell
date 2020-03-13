package com.jluzh.sell.service.impl;

import com.jluzh.sell.dataobject.SellerInfo;
import com.jluzh.sell.form.UserInfoForm;
import com.jluzh.sell.repository.SellerInfoRepository;
import com.jluzh.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 杨鸿坤
 * @description:
 * @date: 2020/01/29
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findBySellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }

    @Override
    public SellerInfo findBySellerInfoByUsernameAndPassword(UserInfoForm form) {
        return repository.findByUsernameAndPassword(form.getUsername(),form.getPassword());
    }
}
