package com.study.springboot.pattern.entity;

import lombok.Data;

@Data
public class FamilyMember {

    private long id;
    private String name;
    private int age;
    private String relation;

}
