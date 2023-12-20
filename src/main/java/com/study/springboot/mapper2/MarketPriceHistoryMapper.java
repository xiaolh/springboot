package com.study.springboot.mapper2;

import com.study.springboot.entity.MaketPriceHistory;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @auther xiaolh
 * @create 2023/3/28
 */
@Mapper
public interface MarketPriceHistoryMapper {

    int insertUrl(@Param("url") String url);

    String getDictionaryValue(String key);

    @MapKey("name")
    List<Map> getUrlList(String name);

    int updateUrl(@Param("name") String name,@Param("id") Integer id);

    int deleteByName(String name);

    int insertBatch(@Param("historyList") List<MaketPriceHistory> historyList);

    @MapKey("date")
    List<Map> getHistoryByName(String name);

}
