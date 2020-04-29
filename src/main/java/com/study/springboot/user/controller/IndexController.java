package com.study.springboot.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class IndexController {

    /** 默认显示首页 */
    @GetMapping("/")
    public String toIndex(){ return "index";}

}
