package com.study.springboot.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.study.springboot.entity.MaketPriceHistory;
import com.study.springboot.mapper2.MarketPriceHistoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @auther xiaolh
 * @create 2023/12/14
 */
@Slf4j
@RestController
@RequestMapping("/collect")
public class CrawlerController {

    @Autowired
    private MarketPriceHistoryMapper historyMapper;

    private RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/dota2/saveUrl")
    public String saveUrl(@RequestParam("name") String name,@RequestParam("url") String url){
        historyMapper.insertUrl(name,url);
        return "SUCCESS";
    }

    @GetMapping("/dota2/itemList")
    public List itemList(){
        List<Map> urlList = historyMapper.getUrlList(null);
        List<String> itemList = new ArrayList<>();
        for (Map map : urlList) {
            itemList.add((String) map.get("name"));
        }
        return itemList;
    }

    @GetMapping("/dota2/getData")
    public String testGetPage(@RequestParam("name") String name) throws ParseException {
        long beginTime = System.currentTimeMillis();
        List<Map> setMapList = historyMapper.getUrlList(name);
        if (CollectionUtil.isEmpty(setMapList)) return "没有找到饰品 | " + name;
        List<String> urlList = new ArrayList<>();
        for (Map map : setMapList) {
            urlList.add((String) map.get("url"));
        }
        List<MaketPriceHistory> historyList = new ArrayList<>();
        String resStr = "";
        for (String url : urlList) {
            url = URLDecoder.decode(url);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
            String cookie = "sessionid=5c2acf9b32afa4cb82a93a9c; timezoneOffset=28800,0; browserid=2864916450327407208; webTradeEligibility=%7B%22allowed%22%3A1%2C%22allowed_at_time%22%3A0%2C%22steamguard_required_days%22%3A15%2C%22new_device_cooldown_days%22%3A0%2C%22time_checked%22%3A1702769233%7D; steamCountry=NL%7Caf4afed5ce611fd165e759be6102f618; steamLoginSecure=76561198139019128%7C%7CeyAidHlwIjogIkpXVCIsICJhbGciOiAiRWREU0EiIH0.eyAiaXNzIjogInI6MEREN18yM0E2NzA0OV85MDNEOCIsICJzdWIiOiAiNzY1NjExOTgxMzkwMTkxMjgiLCAiYXVkIjogWyAid2ViIiBdLCAiZXhwIjogMTcwMzAwNzUyOSwgIm5iZiI6IDE2OTQyNzk0NzYsICJpYXQiOiAxNzAyOTE5NDc2LCAianRpIjogIjBEREZfMjNBNjcwNkJfRDhCRkIiLCAib2F0IjogMTcwMjkxOTQ3NSwgInJ0X2V4cCI6IDE3MjEyMTQ5MTcsICJwZXIiOiAwLCAiaXBfc3ViamVjdCI6ICI5MS4xNDguMjI0Ljk3IiwgImlwX2NvbmZpcm1lciI6ICIxMDMuMTI5LjI1Mi4yMzMiIH0.4IBla1GgCqc7mBo8o1I4LakEaAUKVN89fEzFb_g69zAe_Klp7POYX5TTmyzvwXHAx88r-xsl6qEFXthJhFgNAQ";
            headers.set("Cookie",cookie);
            HttpEntity request = new HttpEntity(headers);
            long pageBeginTime = System.currentTimeMillis();
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            long pageEndTime = System.currentTimeMillis();
            String pageTime = NumberUtil.roundStr(Double.valueOf(pageEndTime - pageBeginTime) / 1000,1);
            String res = exchange.getBody();

            printFormat("page",res);
            String dataStr = null;
            String itemStr = null;

            int dataBeginIndex = res.indexOf("var line1=") + "var line1=".length();
            int dataEndIndex = res.indexOf(";",dataBeginIndex);
            dataStr = res.substring(dataBeginIndex,dataEndIndex);

            printFormat("data",dataStr);
            int itemBeginIndex = res.indexOf("market_buy_commodity_item_display ellipsis") + "market_buy_commodity_item_display ellipsis".length();
            itemBeginIndex = res.indexOf("market_listing_item_name_block",itemBeginIndex) + "market_listing_item_name_block".length();
            itemBeginIndex = res.indexOf("market_listing_item_name",itemBeginIndex) + "market_listing_item_name".length();
            itemBeginIndex = res.indexOf(">",itemBeginIndex) + 1;
            int itemEndIndex = res.indexOf("<",itemBeginIndex);
            itemStr = res.substring(itemBeginIndex,itemEndIndex);
            printFormat("item",itemStr);
            log.info("{} | 网页请求 | {}",itemStr,pageTime);
            JSONArray jsonArray = JSONUtil.parseArray(dataStr);
            resStr = resStr + itemStr + " | " + pageTime + " S | " + jsonArray.size() + "\n";
            historyMapper.deleteByName(itemStr);
            for (Object o : jsonArray) {
                JSONArray temp = (JSONArray) o;
                String dateTime = String.valueOf(temp.get(0));
                Double price = Double.valueOf(temp.get(1).toString());
                Integer count = Integer.valueOf(temp.get(2).toString());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd yyyy HH: +0", Locale.ENGLISH);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+:00:00"));
                Date date = simpleDateFormat.parse(dateTime);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+:08:00"));
                dateTime = DateUtil.format(date,"yyyy-MM-dd HH:mm:ss");
                MaketPriceHistory priceHistory = new MaketPriceHistory();
                priceHistory.setName(itemStr);
                priceHistory.setPrice(price);
                priceHistory.setCount(count);
                priceHistory.setDate(date);
                historyList.add(priceHistory);
            }

        }
        int successCount = historyMapper.insertBatch(historyList);
        Long endTime = System.currentTimeMillis();
        resStr = resStr + "总耗时 | " + NumberUtil.roundStr(Double.valueOf(endTime - beginTime) / 1000,1) + " S" + " | " + successCount;
        return resStr;
    }

    @GetMapping("/dota2/history")
    public Object getDotaItemHistory(@RequestParam("name") String name){
        List<Map> historyList = historyMapper.getHistoryByName(name);
        List<String> dateList = new ArrayList<>();
        List<Double> priceList = new ArrayList<>();
        for (Map map : historyList) {
            Date date = (Date) map.get("date");
            Double price = (Double) map.get("price");
            dateList.add(DateUtil.format(date,"yyyy-MM-dd HH"));
            priceList.add(price);
        }
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("dateList",dateList);
        resMap.put("priceList",priceList);
        resMap.put("count",historyList.size());
        return resMap;
    }

    // ===================================== tool method =====================================

    public void printFormat(String key, String res){
        log.info("===================================== " + key +" begin =====================================");
        log.info(res);
        log.info("===================================== " + key + " end =====================================");
    }

}
