package com.itzixue.exception;

import com.itzixue.enums.ResultEnum;
import lombok.Getter;

/**
 * @author Mr.Dong
 * @create 2019-05-27 11:49
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
