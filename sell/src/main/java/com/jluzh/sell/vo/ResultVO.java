package com.jluzh.sell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author by: hongkun.yang
 * @description: http请求返回的最外层对象（视图对象）
 * @date: 2019/11/30
 */
@Data
public class ResultVO<T> implements Serializable {

    //生成ID保证序列化唯一
    private static final long serialVersionUID = 3792141278866132534L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回具体内容
     */
    private T data;
}
