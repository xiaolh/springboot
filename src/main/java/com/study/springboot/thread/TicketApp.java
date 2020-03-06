package com.study.springboot.thread;

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
    public void sellTicket(){
        this.count --;
        log.info("ADD-{}",count.toString());
    }

    /**
     * 加票
     */
    public void addTicket(){
        this.count ++;
        log.info("SUB-{}",count.toString());
    }

    public static void main(String[] args) {
        TicketApp app = new TicketApp(2000);
        TicketAddThread addThread = new TicketAddThread(app);
        TicketSubThread subThread = new TicketSubThread(app);
        new Thread(addThread).start();
        new Thread(subThread).start();
        log.info("NOW-{}",app.getCount());
    }

}
