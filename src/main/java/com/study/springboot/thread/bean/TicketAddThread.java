package com.study.springboot.thread.bean;

/**
 * @author xiaolh
 * @date 2019/12/5
 */
public class TicketAddThread implements Runnable{

    private TicketApp app;

    public TicketAddThread(TicketApp app){
        this.app = app;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0;i < 2000;i ++){
            app.addTicket();
        }
    }

}
