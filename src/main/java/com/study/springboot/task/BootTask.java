package com.study.springboot.task;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BootTask {

    //@EventListener(ApplicationReadyEvent.class)
    @Async
    public void bootMethd(){
        System.out.println("==================================== listen_1 ====================================");
        while (true){
            try{
                log.info("=========== listen_1 run");
                Thread.sleep(RandomUtil.randomInt(10000,20000));
            }catch (Exception e){
            }
        }
    }

    //@EventListener(ApplicationReadyEvent.class)
    @Async
    public void bootMethd2(){
        System.out.println("==================================== listen_2 ====================================");
        while (true){
            try{
                log.info("=========== listen_2 run");
                Thread.sleep(RandomUtil.randomInt(10000,20000));
            }catch (Exception e){
            }
        }
    }

    //@EventListener(ApplicationReadyEvent.class)
    @Async
    public void bootMethd3(){
        System.out.println("==================================== listen_3 ====================================");
        while (true){
            try{
                log.info("=========== listen_3 run");
                Thread.sleep(RandomUtil.randomInt(10000,20000));
            }catch (Exception e){
            }
        }
    }

    //@EventListener(ApplicationReadyEvent.class)
    @Async
    public void bootMethd4(){
        System.out.println("==================================== listen_4 ====================================");
        while (true){
            try{
                log.info("=========== listen_4 run");
                Thread.sleep(RandomUtil.randomInt(10000,20000));
            }catch (Exception e){
            }
        }
    }

    //@EventListener(ApplicationReadyEvent.class)
    @Async
    public void bootMethd5(){
        System.out.println("==================================== listen_5 ====================================");
        while (true){
            try{
                log.info("=========== listen_5 run");
                Thread.sleep(RandomUtil.randomInt(10000,20000));
            }catch (Exception e){
            }
        }
    }

}
