package com.study.springboot.thread;

/**
 * @author xiaolh
 * @date 2019/12/5
 */
public class TicketSubThread implements Runnable{

    private TicketApp app;

    public TicketSubThread(TicketApp app){
        this.app = app;
    }

    @Override
    public void run() {
        for (int i = 0;i < 2000;i ++){
            app.sellTicket();
        }
    }

}
