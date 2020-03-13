package com.jluzh.sell.aspect;

import com.jluzh.sell.constant.CookieConstant;
import com.jluzh.sell.constant.RedisConstant;
import com.jluzh.sell.exception.SellerAuthorizeException;
import com.jluzh.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: yanghongkun
 * @description: 卖家授权
 * @date: 2020/01/31
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    //验证
    //切入点 以seller开头的Controller中的方法 ，排除用户登录
    @Pointcut("execution(public * com.jluzh.sell.controller.Seller*.*(..))" +
            "&& !execution(public * com.jluzh.sell.controller.SellerUserController.*(..))")
    public void verify() {

    }

    //验证具体实现,在切入点之前
    @Before("verify()")
    public void doVerify() {
        //获取httprequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            //捕捉异常时处理
            throw new SellerAuthorizeException();
        }

        //去redis中查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)){
            log.warn("【登录校验】Redis中查不到token");
            //捕捉异常时处理
            throw new SellerAuthorizeException();
        }

    }

}
