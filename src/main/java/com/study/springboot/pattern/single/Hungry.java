package com.study.springboot.pattern.single;

public class Hungry {

    private static final Hungry instance = new Hungry();

    private Hungry(){}

    public static Hungry getInstance(){
        return instance;
    }

}
