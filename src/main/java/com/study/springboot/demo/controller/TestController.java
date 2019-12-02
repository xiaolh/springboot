package com.study.springboot.demo.controller;

import com.study.springboot.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("generate/orderNo/{prefix}")
    public String generateOrderNo(@PathVariable("prefix") String prefix){
        return StringUtils.generateOrderCode(prefix);
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
