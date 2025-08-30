package com.study.springboot.thread;

import com.study.springboot.service.CollectService;
import com.study.springboot.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestRunnable implements Runnable{

    @Autowired
    private CollectService collectService;

    @Override
    public void run() {
        System.out.println(collectService);
        System.out.println(SpringContextUtil.getContext().getBean(CollectService.class));
    }

}
