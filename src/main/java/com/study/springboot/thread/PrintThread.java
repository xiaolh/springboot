package com.study.springboot.thread;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaolh
 * @date 2020/3/6
 */
public class PrintThread implements Runnable{

    private String content;

    public PrintThread(String content){
        this.content = content;
    }

    @Override
    public void run(){
        for (int i = 0;i < 10;i++){
            try{
                Thread.sleep(200);
            }catch (Exception e){}
            System.out.println(content);
        }
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        PrintThread threadA = new PrintThread("AAA");
        PrintThread threadB = new PrintThread("BBB");
        PrintThread threadC = new PrintThread("CCC");
        executorService.execute(threadA);
        executorService.execute(threadB);
        executorService.execute(threadC);

    }

}
