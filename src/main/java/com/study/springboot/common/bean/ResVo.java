package com.study.springboot.common.bean;

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

    public ResVo success(){
        return new ResVo(Constants.SUCCESS_CODE,Constants.SUCCESS,null);
    }

    public ResVo success(Object data){
        return new ResVo(Constants.SUCCESS_CODE,Constants.SUCCESS,data);
    }

}
