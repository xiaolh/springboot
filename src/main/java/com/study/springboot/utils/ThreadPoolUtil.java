package com.study.springboot.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {

    private static ExecutorService defaultExecutor;

    public static ExecutorService getDefaultThreadPool(){
        if (null == defaultExecutor){
            defaultExecutor = new ThreadPoolExecutor(20,20,0, TimeUnit.SECONDS,new LinkedBlockingQueue<>(200));
        }
        return defaultExecutor;
    }

}
