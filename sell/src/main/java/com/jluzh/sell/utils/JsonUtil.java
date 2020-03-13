package com.jluzh.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author: yanghongkun
 * @description: json工具类
 * @date: 2020/01/20
 */
public class JsonUtil {

     public static String toJson(Object object){
         GsonBuilder gsonBuilder = new GsonBuilder();
         gsonBuilder.setPrettyPrinting();
         Gson gson = gsonBuilder.create();
         return gson.toJson(object);
     }
}
