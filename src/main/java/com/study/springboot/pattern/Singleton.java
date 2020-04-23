package com.study.springboot.pattern;

/**
 * 单例设计模式
 */
public class Singleton {

    private static Singleton instance;

    private Singleton(){}

    public static synchronized Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public void hello(){
        System.out.println("hello singleton!");
    }

}
