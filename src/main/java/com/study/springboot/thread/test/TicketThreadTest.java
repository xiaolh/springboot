package com.study.springboot.thread.test;

import com.study.springboot.thread.bean.TicketAddThread;
import com.study.springboot.thread.bean.TicketApp;
import com.study.springboot.thread.bean.TicketSubThread;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaolh
 * @date 2019/12/5
 */
@Slf4j
public class TicketThreadTest {

    public static void main(String[] args) {
        TicketApp app = new TicketApp(200);
        TicketAddThread addThread = new TicketAddThread(app);
        TicketSubThread subThread = new TicketSubThread(app);
        new Thread(addThread).start();
        new Thread(subThread).start();
        log.info("NOW-{}",app.getCount());
    }

}
