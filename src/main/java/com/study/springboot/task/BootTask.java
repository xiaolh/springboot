package com.study.springboot.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BootTask {

    @EventListener(ApplicationReadyEvent.class)
    public void bootMethd(){
        //System.out.println("==================================== @EventListener Boot Finish ====================================");



    }

}
