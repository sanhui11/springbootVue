package com.fjb.springbootvue.exception;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/03/08/13:40
 * @Description: 自定义一次
 */
@Getter
public class ServiceException extends RuntimeException {
    private String code;
    public ServiceException(String code,String msg){
        super(msg);
        this.code=code;
    }
}
