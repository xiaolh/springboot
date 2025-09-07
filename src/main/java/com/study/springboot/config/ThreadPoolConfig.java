package com.study.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@Component
@Slf4j
public class ThreadPoolConfig {

    @Bean("taskExecutor")
    public Executor defaultExecutor(){
        return new SimpleAsyncTaskExecutor();
    }

    @Bean("bootExecutor")
    public Executor bootExecutor(){
        return new ThreadPoolExecutor(20,20,0, TimeUnit.SECONDS,new LinkedBlockingQueue<>(200));
    }

}
