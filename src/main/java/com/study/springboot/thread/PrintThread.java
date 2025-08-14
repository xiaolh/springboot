package com.study.springboot.thread;

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

    /*@Override
    public Object call() throws Exception {
        for (int i = 0;i < 10;i++){
            try{
                Thread.sleep(200);
            }catch (Exception e){}
            System.out.println(content);
        }
        return "wtf";
    }*/

    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        PrintThread threadA = new PrintThread("AAA");
        PrintThread threadB = new PrintThread("BBB");
        PrintThread threadC = new PrintThread("CCC");
        executorService.submit(threadA); // submit 可以判断认识是否完成
        executorService.execute(threadB);
        executorService.execute(threadC);

        /*FutureTask fta = new FutureTask(threadA);
        FutureTask ftb = new FutureTask(threadB);
        FutureTask ftc = new FutureTask(threadC);

        new Thread(fta).start();
        new Thread(ftb).start();
        new Thread(ftc).start();

        System.out.println(fta.get());*/

    }

}
