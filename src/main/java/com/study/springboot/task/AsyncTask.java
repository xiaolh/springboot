package com.study.springboot.task;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncTask{

    @Async
    public void bootMethd(){
        log.info("==================================== task_0 ====================================");
        while (true){
            try{
                log.info("=========== task_0 run");
                Thread.sleep(RandomUtil.randomInt(10000,20000));
            }catch (Exception e){
            }
        }
    }

    @Async("bootExecutor")
    public void bootMethd1(){
        log.info("==================================== task_1 ====================================");
        while (true){
            try{
                log.info("=========== task_1 run");
                Thread.sleep(RandomUtil.randomInt(10000,20000));
            }catch (Exception e){
            }
        }
    }

    @Async("bootExecutor")
    public void bootMethd2(){
        log.info("==================================== task_2 ====================================");
        while (true){
            try{
                log.info("=========== task_2 run");
                Thread.sleep(RandomUtil.randomInt(10000,20000));
            }catch (Exception e){
            }
        }
    }

}
