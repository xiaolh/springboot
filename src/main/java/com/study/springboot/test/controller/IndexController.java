package com.study.springboot.test.controller;
import com.study.springboot.basic.utils.UniversalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("index")
@Slf4j
@ApiIgnore
public class IndexController {

    @GetMapping("")
    public String index(){
        return "<h1>This is index !</h1>";
    }

    @GetMapping("generate/orderNo/{prefix}")
    public String generateOrderNo(@PathVariable("prefix") String prefix){
        return UniversalUtils.generateOrderCode(prefix);
    }

}
