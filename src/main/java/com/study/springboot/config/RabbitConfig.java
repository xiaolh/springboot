package com.study.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaolh
 * @date 2019/12/4
 */
@Configuration
@Slf4j
public class RabbitConfig {

    @Bean
    public DirectExchange defaultExchange() {
        boolean durable = true;
        boolean autoDelete = false;
        return new DirectExchange("defaultExchange", durable, autoDelete);
    }

    /**
     * 测试队列
     * @return
     */
    @Bean
    public Queue testQueue() {
        boolean durable = true;
        boolean exclusive = false;
        boolean autoDelete = false;
        return new Queue("testQueue", durable, exclusive, autoDelete);
    }

    /**
     * 测试绑定
     * @return
     */
    @Bean
    public Binding failPlanBinding() {
        return BindingBuilder.bind(testQueue()).to(defaultExchange()).with("testKey");
    }

}
