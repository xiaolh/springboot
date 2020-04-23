package com.study.springboot.pattern.entity;

public enum HeroEnum {

    AM("AM","敌法师"),
    SF("SF","影魔"),
    CK("CK","混沌骑士");

    private String code;
    private String chnName;

    HeroEnum(String code, String chnName){
        this.code = code;
        this.chnName = chnName;
    }

    public String getCode() {
        return code;
    }

    public String getChnName() {
        return chnName;
    }
}
