package com.study.springboot.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BootRunner  implements ApplicationRunner {

    @Autowired
    private AsyncTask asyncTask;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("========================================= boot runner begin");
        asyncTask.bootMethd();
        asyncTask.bootMethd1();
        asyncTask.bootMethd2();

    }

}
