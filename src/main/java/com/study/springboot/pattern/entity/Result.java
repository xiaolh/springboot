package com.study.springboot.pattern.entity;

/**
 * 结果
 */
public class Result {

    private Boolean status;
    private String message;
    private Object data;

    public Result() {}

    public Result(Boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static Result ok(){
        return new Result(true,null,null);
    }

    public static Result ok(Object data){

        return new Result(true,"ok",data);
    }

    public static Result fail(String msg){
        return new Result(false,msg,null);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
