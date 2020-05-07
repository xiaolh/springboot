package com.study.springboot.test;

import com.study.springboot.basic.utils.UniversalUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaolh
 * @date 2020/3/6
 */
@Slf4j
@Component
public class TestClass {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void afsdf(){
        System.out.println(new Date().getTime());
        System.out.println(String.format("第[%d]笔计划没有成功",12));
        for (int i = 0;i < 10;i++){
            System.out.println(UniversalUtils.generateOrderCode("JD"));
        }
    }

    //@Scheduled(fixedRate = 20 * 60 * 1000)
    public void testRedis(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name","xlh");
        redisTemplate.expire("name",2, TimeUnit.SECONDS);
        String name = (String)valueOperations.get("name");
        log.info(name);
    }

    @Test
    public void fuli(){
        Double balance = 0d;
        Double yearAmount = 200000d;
        Double profit = 0.2;
        Integer year = 5;
        for (int i =0;i < year;i++){
            balance += yearAmount;
            balance *= (1 +profit);
        }
        log.info("年入 {}",yearAmount.toString());
        log.info("年化 {}%",profit.intValue() * 100);
        log.info("时间 {} 年",year);
        log.info("复利后 {}",balance);
    }

}
