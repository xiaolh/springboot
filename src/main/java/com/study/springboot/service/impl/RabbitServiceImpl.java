package com.study.springboot.service.impl;

import com.study.springboot.service.RabbitService;
import com.study.springboot.entity.User;
import com.study.springboot.mapper.UserMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author xiaolh
 * @date 2019/12/4
 */
//@Service
public class RabbitServiceImpl implements RabbitService {

    //@Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendUser(User user){
        rabbitTemplate.convertAndSend("defaultExchange", "testKey", user);
    }

    //@Autowired
    UserMapper userMapper;

    //@Scheduled(fixedRate = 20 * 60 * 1000)
    public void testSend(){
        User user = userMapper.selectById(1);
        sendUser(user);
    }

}
