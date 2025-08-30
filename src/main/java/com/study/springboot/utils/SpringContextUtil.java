package com.study.springboot.utils;

import lombok.Getter;
import org.springframework.context.ApplicationContext;

public class SpringContextUtil{

    @Getter
    private static ApplicationContext context;

    public static void setContext(ApplicationContext context) {
        SpringContextUtil.context = context;
    }

    public <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }

}
