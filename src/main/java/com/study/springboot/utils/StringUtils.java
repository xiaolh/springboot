package com.study.springboot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

    /**
     * 生成订单号
     * @param prefix
     * @return
     */
    public static String generateOrderCode(String prefix){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuilder builder = new StringBuilder();
        builder.append(prefix == null ? "OR" : prefix.toUpperCase());
        builder.append("-");
        builder.append(formatter.format(new Date()));
        builder.append("-");
        builder.append((int)((Math.random() * 9 + 1) * 1000000));
        return builder.toString();
    }

}
