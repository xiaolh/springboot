package com.study.springboot.pattern.single;

public class Lazy {

    private volatile static Lazy lazy;

    private Lazy (){}

    public static Lazy getInstance(){
        if (null == lazy){
            synchronized (Lazy.class){
                if (null == lazy) {
                    lazy = new Lazy();
                }
            }
        }
        return lazy;
    }

}
