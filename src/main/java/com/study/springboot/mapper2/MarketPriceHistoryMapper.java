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

    int updateDictionary(@Param("key") String key,@Param("value") String value);

    @MapKey("id")
    List<Map> getDictionaryValue(String key);

    @MapKey("name")
    List<Map> getUrlList(@Param("name") String name,@Param("collectFlag") Boolean collectFlag);

    int updateUrl(@Param("name") String name,@Param("id") Integer id);

    int deleteByName(String name);

    int insertBatch(@Param("historyList") List<MaketPriceHistory> historyList);

    @MapKey("date")
    List<Map> getHistoryByName(String name);

    int deleteNoNameHistory();

}
