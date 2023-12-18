package com.study.springboot.entity;

import lombok.Data;

import java.util.Date;

/**
 * @auther xiaolh
 * @create 2023/12/17
 */
@Data
public class MaketPriceHistory {

    private Integer id;

    private String name;

    private Date date;

    private Double price;

    private Integer count;

}
