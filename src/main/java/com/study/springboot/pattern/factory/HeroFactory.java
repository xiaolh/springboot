package com.study.springboot.pattern.factory;

public class HeroFactory {

    /** 英雄枚举 */
    public enum HeroCode{
        AM,
        SF
    }

    public static Hero getHero(HeroCode code){

        Hero hero;
        switch (code){
            case AM:
                hero = new AM();
                break;
            case SF:
                hero = new SF();
                break;
            default:
                hero = null;
        }
        return hero;
    }

}
