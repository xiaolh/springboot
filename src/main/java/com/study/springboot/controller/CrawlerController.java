package com.study.springboot.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @auther xiaolh
 * @create 2023/12/14
 */
@RestController
@RequestMapping("/crawler")
public class CrawlerController {

    @GetMapping("/testPage")
    public String testGetPage(){
        String res = null;
        try{
            String url = "https://steamcommunity.com/market/listings/570/Vigil Triumph";
            RestTemplate template = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");

            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<String> exchange = template.exchange(url, HttpMethod.GET, request, String.class);
            res = exchange.getBody();

            System.out.println("===================================== page begin =====================================");
            System.out.println(res);
            System.out.println("===================================== page end =====================================");

        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

}
