package com.study.springboot.pattern;

import com.study.springboot.pattern.factory.Hero;
import com.study.springboot.pattern.factory.HeroFactory;

public class PatternTest {

    public static void main (String[] args){

        /** 单例模式 */
        Singleton.getInstance().hello();
        /** 工厂模式 */
        Hero am = HeroFactory.getHero(HeroFactory.HeroCode.AM);
        Hero sf = HeroFactory.getHero(HeroFactory.HeroCode.SF);
        am.skill();
        sf.skill();
    }

}
