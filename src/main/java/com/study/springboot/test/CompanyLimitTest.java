package com.study.springboot.test;

import cn.hutool.core.date.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @auther xiaolh
 * @create 2023/10/27
 */
public class CompanyLimitTest {

    private DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 判断是否为交易日
     * @param localDate
     * @return
     */
    public Boolean isTradeDay(LocalDate localDate){
        return true;
    }

    /**
     * 获取前一个交易日
     * @param date
     * @return
     */
    public String getPreTradeDay(Date date){
        return "";
    }

    /**
     * 获取下一个交易日
     * @param date
     * @return
     */
    public String getNextTradeDay(Date date){
        return "";
    }

    // =================================================================================================================

    /**
     * 判断当前时间是否为 date 的交易时间
     * @param date 可以为任何日期不一定为交易日
     * @return
     */
    public Boolean nowIsTheDateTradeTime(String date){

        // date 不是交易日，直接返回 false

        // date 是交易日 ----------------------------------------------------

        // date 和当前日期一致 & 是交易日 & 时间在 0：00 - 16：00

        // date 和当前日期不一致
            // 1 当前时间是 date 的上一个交易日 & 21：00 - 24：00
            // 2 当前时间是 date 的上一个交易日 + 1 日 & 0：00 - 3：00

        if (!isTradeDay(LocalDate.parse(date, yyyyMMdd))) return false;

        LocalDateTime now = LocalDateTime.now();
        if (now.format(yyyyMMdd).equals(date)){
            // 今日日盘
            if (now.getHour() >= 0 && now.getHour() <= 15) return true;
        }else {
            // 今日夜盘
            String preTradeDay = getPreTradeDay(DateUtil.parse(date, "yyyyMMdd"));
            if (preTradeDay.equals(now.format(yyyyMMdd))){
                if (now.getHour() >= 21 && now.getHour() <= 24) return true;
            }else {
                String preTradeDayTommorow = LocalDate.parse(preTradeDay,yyyyMMdd).plusDays(1).format(yyyyMMdd);
                if (preTradeDayTommorow.equals(now.format(yyyyMMdd)) && now.getHour() >= 0 && now.getHour() <= 3) return true;
            }
        }

        return false;
    }

    /**
     * 触发该方法时间段 周一到周六 0：00 - 16：00 21：00 - 24：00
     * @return 当前时间所属于的交易日期
     */
    public String calculateRanking(){

        // 如果当前时间是交易日
            // 1，时间在 0：00 - 16：00 实时排序日期为当天
            // 2，时间在 21：00 - 24：00 实时排序日期为下一个交易日

        // 如果当前时间不是交易日
            // 1，判断昨天是不是交易日，如果是并且时间在 0：00 - 3：00（周一的夜盘，在周六） 实时排序日期为下一个交易日

        String date = null;
        LocalDateTime now = LocalDateTime.now();
        if (isTradeDay(now.toLocalDate())){
            if (now.getHour() <= 15) date = now.format(yyyyMMdd);
            if (now.getHour() >= 21) date = getNextTradeDay(DateUtil.parse(now.format(yyyyMMdd),yyyyMMdd));
        }else {
            String yesterday = now.minusDays(1).format(yyyyMMdd);
            if (isTradeDay(LocalDate.parse(yesterday,yyyyMMdd))) {
                if (now.getHour() <= 3){
                    date = getNextTradeDay(DateUtil.parse(yesterday,yyyyMMdd));
                }
            }
        }
        return date;
    }

}
