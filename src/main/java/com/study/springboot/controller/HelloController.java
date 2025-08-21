package com.study.springboot.controller;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("download");
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i <= 100000; i++) {
            JSONObject obj = new JSONObject();
            obj.put("one",1);
            obj.put("two",i + 1);
            obj.put("three",i + 2);
            obj.put("four",i + 3);
            obj.put("five",i + 4);
            obj.put("six",i + 5);
            obj.put("seven",i + 6);
            obj.put("eight",i + 7);
            obj.put("night",i + 8);
            obj.put("ten",i + 9);
            builder.append(obj);
            if (i != 100000) builder.append(obj + "\n");
        }

        String fileName = URLEncoder.encode("测试.jsonl","UTF-8");
        response.setHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream");

        response.getOutputStream().write(builder.toString().getBytes());
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    @GetMapping("read")
    public void read() throws Exception {
        long begin = System.currentTimeMillis();
        FileInputStream inputStream = null;
        InputStreamReader streamReader = null;
        BufferedReader bufferedReader = null;

        try{
            inputStream = new FileInputStream("D://测试.jsonl");
            streamReader = new InputStreamReader(inputStream,"UTF-8");
            bufferedReader = new BufferedReader(streamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        }catch (Exception e){
            IoUtil.close(inputStream);
            IoUtil.close(streamReader);
            IoUtil.close(bufferedReader);
        }
        long end = System.currentTimeMillis();
        System.out.println("=========================== cost time ms = " + (end - begin));
    }

    // 写文件
    @GetMapping("write")
    public void write(HttpServletResponse response) throws IOException {
        long begin = System.currentTimeMillis();
        String filePath = "D://测试.jsonl";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            JSONObject obj = new JSONObject();
            for (int j = 0; j < 10; j++) {
                String index = i + "_" + j;
                obj.put(index,"值" + index);
            }
            builder.append(obj);
            if (i != 99999) builder.append("\n");
        }
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(builder.toString().getBytes("UTF-8"));
        outputStream.close();
        long end = System.currentTimeMillis();
        System.out.println("=========================== cost time ms = " + (end - begin));
    }

}
