package com.study.springboot.basic.entity;

public enum Error {

    FAIL(-1,"操作失败"),

    // 用户
    USER_NOT_FOUND(1000,"没有找到用户")
    ;

    private Integer code;
    private String msg;

    Error(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
