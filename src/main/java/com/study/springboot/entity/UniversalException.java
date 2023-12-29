package com.study.springboot.entity;

import lombok.NoArgsConstructor;

/**
 * @author xiaolh
 * @date 2020/3/6
 */
@NoArgsConstructor
public class UniversalException extends RuntimeException{

    private Integer code;
    private String msg;

    public UniversalException(Error error){
        code = error.getCode();
        msg = error.getMsg();
    }

    public String getCode(){
        return code.toString();
    }

    public String getMsg(){
        return msg;
    }

}
