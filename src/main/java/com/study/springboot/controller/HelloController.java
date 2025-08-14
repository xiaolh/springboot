package com.study.springboot.controller;

import cn.hutool.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@ApiIgnore
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

    // 通用下载
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("id",1);
        obj.put("type",1);

        StringBuilder builder = new StringBuilder();
        builder.append(obj + "\n");
        builder.append(obj);

        String fileName = URLEncoder.encode("测试.jsonl","UTF-8");
        response.setHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream");

        response.getOutputStream().write(builder.toString().getBytes());
    }

}
