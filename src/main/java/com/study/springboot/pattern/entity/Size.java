package com.study.springboot.pattern.entity;

public enum Size {

    /** 小 */
    SMALL("S"),
    /** 中 */
    MEDIUM("M"),
    /** 大 */
    LARGE("L"),
    /** 特大 */
    EXTRA_LARGE("X");

    private String code;

    private Size(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}


