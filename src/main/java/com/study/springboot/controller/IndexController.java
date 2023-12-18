package com.study.springboot.controller;

import com.study.springboot.mapper.StarMapper;
import com.study.springboot.mapper2.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class IndexController {

    @Autowired
    private StarMapper starMapper;
    @Autowired
    private CardMapper cardMapper;

    /** 默认显示首页 */
    @GetMapping("/v1")
    @ResponseBody
    public String toIndex(){ return starMapper.findById().toString();}

    /** 默认显示首页 */
    @GetMapping("/v2")
    @ResponseBody
    public String toIndex2(){ return cardMapper.findById().toString();}

}
