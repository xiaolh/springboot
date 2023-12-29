package com.study.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaolh
 * @date 2020/3/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResVo {

    private String code;
    private String msg;
    private Object data;

    public static ResVo success(Object data){
        return new ResVo(Constants.SUCCESS_CODE,Constants.SUCCESS,data);
    }

    public static ResVo fail(String code,String msg,Object data){
        return new ResVo(code,msg,data);
    }

}
