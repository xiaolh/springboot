package com.study.springboot.test.controller;
import com.study.springboot.basic.utils.UniversalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @GetMapping("index")
    public String index(){
        return "This is index !";
    }

    @GetMapping("generate/orderNo/{prefix}")
    public String generateOrderNo(@PathVariable("prefix") String prefix){
        return UniversalUtils.generateOrderCode(prefix);
    }

}
