package com.study.springboot.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

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
    private AtomicInteger count;

    public TicketApp(){}

    /**
     * 买票
     */
    public void sellTicket(){
        this.count.decrementAndGet();
        log.info("SUB-{}",count.toString());
    }

    /**
     * 加票
     */
    public void addTicket(){
        this.count.incrementAndGet();
        log.info("ADD-{}",count.toString());
    }

    @Test
    public void test1() throws Exception{

        TicketApp app = new TicketApp();
        app.setCount(new AtomicInteger(200));

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 200;i ++){
                    app.addTicket();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 200;i ++){
                    app.sellTicket();
                }
            }
        }).start();
        Thread.sleep(2000);
        log.info("NOW-{}",app.getCount());

    }

}
