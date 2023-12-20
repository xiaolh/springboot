package com.study.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.study.springboot.entity.MaketPriceHistory;
import com.study.springboot.mapper2.MarketPriceHistoryMapper;
import com.study.springboot.service.CollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @auther xiaolh
 * @create 2023/12/20
 */
@Slf4j
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private MarketPriceHistoryMapper historyMapper;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    public void collectJob() throws ParseException {
        long beginTime = System.currentTimeMillis();
        List<Map> setMapList = historyMapper.getUrlList(null);
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
            String cookie = "sessionid=5c2acf9b32afa4cb82a93a9c; timezoneOffset=28800,0; browserid=2864916450327407208; webTradeEligibility=%7B%22allowed%22%3A1%2C%22allowed_at_time%22%3A0%2C%22steamguard_required_days%22%3A15%2C%22new_device_cooldown_days%22%3A0%2C%22time_checked%22%3A1702769233%7D; steamCountry=US%7C820fbb8ebb6df356703475a18b44389e; steamLoginSecure=76561198139019128%7C%7CeyAidHlwIjogIkpXVCIsICJhbGciOiAiRWREU0EiIH0.eyAiaXNzIjogInI6MEREN18yM0FCQ0RCNl84NUJFMSIsICJzdWIiOiAiNzY1NjExOTgxMzkwMTkxMjgiLCAiYXVkIjogWyAid2ViIiBdLCAiZXhwIjogMTcwMzE1NjgwMywgIm5iZiI6IDE2OTQ0MzAyMTUsICJpYXQiOiAxNzAzMDcwMjE1LCAianRpIjogIjBERENfMjNBQkNEQjhfQzBGMEQiLCAib2F0IjogMTcwMzA3MDIxNCwgInJ0X2V4cCI6IDE3MjEzNDEwNjgsICJwZXIiOiAwLCAiaXBfc3ViamVjdCI6ICI4MS4xNzEuNjIuMTEyIiwgImlwX2NvbmZpcm1lciI6ICI0NS4xOTUuMTguMTQyIiB9.ElISCYlPszuxf1EPoHIJvlyzITg-pSM42ViIjXzPyfd4lpgejvlppZGW_GMzdh7mDmn9eK2SbAsmgH-b-BT9AQ";
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
        log.info(resStr);
    }

    @Override
    public List getItemList() {
        List<Map> urlList = historyMapper.getUrlList(null);
        List<String> itemList = new ArrayList<>();
        for (Map map : urlList) {
            itemList.add((String) map.get("name"));
        }
        return itemList;
    }

    @Override
    public Object getHistory(String name) {
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

    @Override
    public void insertUrl(String name, String url) {
        historyMapper.insertUrl(name,url);
    }

    public void printFormat(String key, String res){
        log.info("===================================== " + key +" begin =====================================");
        log.info(res);
        log.info("===================================== " + key + " end =====================================");
    }

}
