package com.study.springboot.service.impl;

import com.rabbitmq.client.Channel;
import com.study.springboot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;

@Slf4j
//@Component
public class TestReceiver {

    //@RabbitListener(queues="testQueue")
    public void receive(@Payload User user, Channel channel, Message message) {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error("发生异常 {}",e.getMessage());
            e.printStackTrace();
        }
        if(user == null) {
            return;
        }
        log.info(user.toString());
    }

}
