package com.study.springboot.rabbit;

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
        return new DirectExchange("defaultExchange", true, false);
    }

    /**
     * 测试队列
     */
    @Bean
    public Queue testQueue() {
        return new Queue("testQueue", true, false, false);
    }

    /**
     * 测试绑定
     */
    @Bean
    public Binding failPlanBinding() {
        return BindingBuilder.bind(testQueue()).to(defaultExchange()).with("testKey");
    }

}
