package com.jluzh.sell.controller;

import com.jluzh.sell.constant.CookieConstant;
import com.jluzh.sell.constant.RedisConstant;
import com.jluzh.sell.dataobject.SellerInfo;
import com.jluzh.sell.enums.ResultEnum;
import com.jluzh.sell.form.ProductFrom;
import com.jluzh.sell.form.UserInfoForm;
import com.jluzh.sell.service.SellerService;
import com.jluzh.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: yanghongkun
 * @description: 卖家用户
 * @date: 2020/01/30
 */
@Controller
@RequestMapping("/seller")
@Component
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @PostMapping("/verification")
    public ModelAndView login(@Valid UserInfoForm from,
                              BindingResult bindingResult,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        //1.openid去和数据库里的数据匹配
        //SellerInfo sellerInfo = sellerService.findBySellerInfoByOpenid(openid);
        SellerInfo sellerInfo = sellerService.findBySellerInfoByUsernameAndPassword(from);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error");
        }
        //2.设置token至redis
        //生成UUID作为token
        String token = UUID.randomUUID().toString();
        //设置过期时间
        Integer expire = RedisConstant.EXPIRE;
        //key,value,过期时间，时间单位
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), sellerInfo.getUsername(), expire, TimeUnit.SECONDS);
        //3.设置token至cookies,校验时从cookies中拿到token,再去redis中校验
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
        //4.校验成功，跳转
        return new ModelAndView("redirect:" + "http://localhost:9999/sell/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map) {
        //1.从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2.清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3.清除cookie,过期时间设置为0
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/login");
        return new ModelAndView("common/success",map);
    }
}
