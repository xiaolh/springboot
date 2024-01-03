package com.study.springboot.controller;
import com.study.springboot.utils.UniversalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("utils")
@Slf4j
@ApiIgnore
public class UtilsController {

    @GetMapping("")
    public String index(){
        return "<h1>This is utils !</h1>";
    }

    @GetMapping("generate/orderNo/{prefix}")
    public String generateOrderNo(@PathVariable("prefix") String prefix){
        return UniversalUtils.generateOrderCode(prefix);
    }

}
