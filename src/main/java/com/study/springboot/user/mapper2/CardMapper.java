package com.study.springboot.user.mapper2;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @auther xiaolh
 * @create 2023/3/28
 */
@Mapper
public interface CardMapper {

    List<HashMap> findById();

}
