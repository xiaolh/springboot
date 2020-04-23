package com.study.springboot.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("exception")
    public String invodeException(){
        Object object = null;
        object.toString();
        return "wtf";
    }

}
