package com.jluzh.sell.exception;

import com.jluzh.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * @author by: hongkun.yang
 * @description:
 * @date: 2019/12/04 14:05
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        //将message的内容传递到父类的构造方法中
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
