package com.study.springboot.controller;

import com.study.springboot.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther xiaolh
 * @create 2023/12/14
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @PostMapping("/dota2/saveUrl")
    public String saveUrl(@RequestParam("url") String url){
        collectService.insertUrl(url);
        return "SUCCESS";
    }

    @GetMapping("/dota2/getData")
    public String getData() throws Exception{
        collectService.collectData();
        return "SUCCESS";
    }

    @GetMapping("/dota2/itemList")
    public List itemList(){
        return collectService.getItemList();
    }

    @GetMapping("/dota2/history")
    public Object getDotaItemHistory(@RequestParam("name") String name){
        return collectService.getHistory(name);
    }

}
