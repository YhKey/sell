package com.jluzh.sell.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yanghongkun
 * @description: 异常处理类
 * @date: 2020/02/03
 */
//让springboot标识为异常处理类 ，直接跟前端页面做交互
@ControllerAdvice
public class GlobalExceptionHandler {

    //编写多个异常处理方法 ，针对一个service写一个不同的处理方法 ，catch住不同的异常
    /*@ExceptionHandler(value = Exception.class)
    //返回错误信息
    @ResponseBody
    public Map<String, Object> exceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", false);
        modelMap.put("errMsg", e.getMessage());
        return modelMap;
    }*/

}
