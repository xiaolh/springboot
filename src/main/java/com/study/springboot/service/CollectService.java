package com.study.springboot.service;

import java.text.ParseException;
import java.util.List;

/**
 * @auther xiaolh
 * @create 2023/12/20
 */
public interface CollectService {

    void collectData() throws ParseException;

    List getItemList();

    Object getHistory(String name);

    void insertUrl(String url);

    void saveTradeHistory(String name, Integer position, Double price, Integer count);

}
