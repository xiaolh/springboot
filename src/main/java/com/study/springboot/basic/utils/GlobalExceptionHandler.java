package com.study.springboot.basic.utils;

import com.study.springboot.basic.bean.Error;
import com.study.springboot.basic.bean.UniversalException;
import com.study.springboot.basic.bean.ResVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiaolh
 * @date 2020/3/6
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResVo defaultExceptionHandler(HttpServletRequest request,Exception e){
        return ResVo.fail("-1",e.getClass().getName(),Error.FAIL);
    }

    @ExceptionHandler(value = UniversalException.class)
    public ResVo commonExceptionHandler(HttpServletRequest request,UniversalException e){
        return ResVo.fail(e.getCode(),e.getMsg(), Error.FAIL);
    }

}
