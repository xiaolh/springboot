package com.study.springboot.thread.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaolh
 * @date 2019/12/5
 */
@Data
@Slf4j
public class TicketApp {

    /**
     * 数量
     */
    private Integer count = 10000;

    public TicketApp(Integer count){
        this.count = count;
    }

    /**
     * 买票
     */
    public synchronized void sellTicket(){
        this.count --;
        log.info("ADD-{}",count.toString());
    }

    /**
     * 加票
     */
    public synchronized void addTicket(){
        this.count ++;
        log.info("SUB-{}",count.toString());
    }

}
