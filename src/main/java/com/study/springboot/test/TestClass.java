package com.study.springboot.test;

import com.study.springboot.basic.utils.UniversalUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

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

}
