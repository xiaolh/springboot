package com.study.springboot.thread;

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

        PrintThread threadA = new PrintThread("AAA");
        PrintThread threadB = new PrintThread("BBB");
        PrintThread threadC = new PrintThread("CCC");
        new Thread(threadA).start();
        new Thread(threadB).start();
        new Thread(threadC).start();

    }

}
