package com.study.springboot.test;

import com.study.springboot.utils.UniversalUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
        Double balance = 100000d;
        Double yearAmount = 120000d;
        Integer year = 15;
        Double more = 1.2;

        log.info("起始 {}",balance.toString());
        log.info("起始年鑫 {}",yearAmount.toString());
        log.info("年化 {}%",Double.valueOf((more - 1)) * 100);
        log.info("时间 {} 年",year);

        for (int i =0;i < year;i++){
            yearAmount *= more;
            balance += yearAmount;
            balance *= more;
        }
        log.info("复利后 {} 万",new BigDecimal(balance / 10000).setScale(0, RoundingMode.HALF_UP).toString());
    }

    @Test
    public void testMath(){
        System.out.println(Math.round(1.2));
        System.out.println(Math.round(1.5));
        System.out.println(Math.round(1.6));
        System.out.println(Math.round(-1.2));
        System.out.println(Math.round(-1.5));
        System.out.println(Math.round(-1.6));
    }

    @Test
    public void testForeach(){
        List<String> tempList = new ArrayList<>();
        tempList.add("a");
        tempList.add("b");
        tempList.add("c");
        tempList.add("d");
        tempList.add("e");
        /*for (int i=0;i<tempList.size();i++){
            if (tempList.get(i).equals("c")){
                tempList.remove(i);
                continue;
            }
            System.out.println(tempList.get(i));
        }*/
        Iterator<String> iterator = tempList.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if (next.equals("c")){
                iterator.remove();
                continue;
            }
            System.out.println(next);
        }
    }


}
