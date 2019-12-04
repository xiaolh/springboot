package com.study.springboot.rabbit.service.impl;

import com.rabbitmq.client.Channel;
import com.study.springboot.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class PlanAutoRepayReceiver {

    @RabbitListener(queues="testQueue")
    public void receive(@Payload User user, Channel channel, Message message) {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error("计划自动还款发生异常"+e.getMessage());
            e.printStackTrace();
        }
        if(user == null) {
            return;
        }
        try{
            log.info(user.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
