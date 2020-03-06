package com.study.springboot.basic.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Date;

public class UniversalUtils {

    /**
     * 生成订单号
     * @param prefix 前缀
     * @return 订单号
     */
    public static String generateOrderCode(String prefix){
        StringBuilder builder = new StringBuilder();
        builder.append(prefix == null ? "OR" : prefix.toUpperCase());
        builder.append("-");
        builder.append(DateUtil.format(new Date(),"yyyyMMddHHmmss"));
        builder.append("-");
        builder.append(RandomUtil.randomNumbers(7));
        return builder.toString();
    }

}
