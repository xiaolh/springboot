package com.study.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @auther xiaolh
 * @create 2023/3/28
 */
@Mapper
public interface StarMapper {

    List<HashMap> findById();

}
