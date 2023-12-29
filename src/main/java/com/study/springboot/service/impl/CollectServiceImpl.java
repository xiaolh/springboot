package com.study.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.study.springboot.entity.Constants;
import com.study.springboot.entity.MaketPriceHistory;
import com.study.springboot.mapper2.MarketPriceHistoryMapper;
import com.study.springboot.service.CollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
import java.time.Duration;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Scheduled(cron = "0 0,20,40 * ? * ? ")
    public void collectData() throws ParseException {
        log.info("================================ collectJob start " + DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss") + " ================================");
        List<Map> setMapList = historyMapper.getUrlList(null,true);
        int deleteCount = historyMapper.deleteNoNameHistory();
        if (deleteCount > 0) log.info("删除无效数据 | {}",deleteCount);
        for (Map map : setMapList) {
            String url = (String) map.get("url");
            String name = (String) map.get("name");
            Integer id = (Integer) map.get("id");
            url = URLDecoder.decode(url);
            List<MaketPriceHistory> historyList = new ArrayList<>();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
            HttpEntity request = new HttpEntity(headers);
            long pageBeginTime = System.currentTimeMillis();
            ResponseEntity<String> exchange = null;
            try{
                exchange = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            }catch (Exception e){
                log.error("网页请求失败 | {} | {} | {} | {}",e.getClass().getName(),e.getMessage(),name,url);
                e.printStackTrace();
                continue;
            }
            long pageEndTime = System.currentTimeMillis();
            String pageTime = NumberUtil.roundStr(Double.valueOf(pageEndTime - pageBeginTime) / 1000,1);
            String res = exchange.getBody();
            if (res.contains("在获取该物品的列表时发生了一个错误。请稍后再试。") && StrUtil.isBlank(name)) {
                log.error("网页返回错误 | {}",url);
                continue;
            }

            String dataStr = null;
            String itemStr = null;

            int dataBeginIndex = res.indexOf("var line1=") + "var line1=".length();
            int dataEndIndex = res.indexOf(";",dataBeginIndex);
            dataStr = res.substring(dataBeginIndex,dataEndIndex);

            int itemBeginIndex = res.indexOf("market_buy_commodity_item_display ellipsis") + "market_buy_commodity_item_display ellipsis".length();
            itemBeginIndex = res.indexOf("market_listing_item_name_block",itemBeginIndex) + "market_listing_item_name_block".length();
            itemBeginIndex = res.indexOf("market_listing_item_name",itemBeginIndex) + "market_listing_item_name".length();
            itemBeginIndex = res.indexOf(">",itemBeginIndex) + 1;
            int itemEndIndex = res.indexOf("<",itemBeginIndex);
            itemStr = res.substring(itemBeginIndex,itemEndIndex);
            if (StrUtil.isBlank(name)) historyMapper.updateUrl(itemStr,id);
            if (StrUtil.isBlank(itemStr)) itemStr = name;

            JSONArray jsonArray = JSONUtil.parseArray(dataStr);
            log.info("网页请求 | {} | {} S | {}",itemStr,pageTime,jsonArray.size());
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
            historyMapper.insertBatch(historyList);
        }
        log.info("================================ collectJob end " + DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss") + " ================================");
    }

    @Override
    public List getItemList() {
        List<Map> urlList = historyMapper.getUrlList(null,null);
        List<String> itemList = new ArrayList<>();
        for (Map map : urlList) {
            itemList.add((String) map.get("name"));
        }
        return itemList;
    }

    @Override
    public Object getHistory(String name) {
        double rate = cnyUsdRate();
        List<Map> historyList = historyMapper.getHistoryByName(name);
        List<String> dateList = new ArrayList<>();
        List<Double> priceList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        for (Map map : historyList) {
            Date date = (Date) map.get("date");
            String price = NumberUtil.roundStr((Double) map.get("price") / rate,2);
            Integer count = (Integer) map.get("count");
            dateList.add(DateUtil.format(date,"yyyy-MM-dd HH:00"));
            priceList.add(Double.valueOf(price));
            countList.add(count);

        }
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("dateList",dateList);
        resMap.put("priceList",priceList);
        resMap.put("volumeList",countList);
        resMap.put("count",historyList.size());
        return resMap;
    }

    @Override
    public void insertUrl(String url) {
        historyMapper.insertUrl(url);
    }

    public Double cnyUsdRate(){
        // redis
        String rateStr = (String) redisTemplate.opsForValue().get(Constants.CNY_USD_RATE);
        if (StrUtil.isNotBlank(rateStr)) return Double.valueOf(rateStr);
        // http update rate
        long beginTime = System.currentTimeMillis();
        String ratePage = restTemplate.getForObject("https://www.xe.com/zh-CN/currencyconverter/convert/?Amount=1&From=USD&To=CNY",String.class);
        int beginIndex = ratePage.indexOf("1 CNY = ") + 8;
        int endIndex = ratePage.indexOf(" USD",beginIndex);
        rateStr = ratePage.substring(beginIndex,endIndex);
        long endTime = System.currentTimeMillis();
        log.info("xe rate | 1 CNY = {} USD | {}",rateStr,(endTime - beginTime) / 1000);
        redisTemplate.opsForValue().set(Constants.CNY_USD_RATE,rateStr, Duration.ofMinutes(20));
        return new Double(rateStr);
    }

}
