package com.study.springboot.entity;

import lombok.Data;

@Data
public class TradeHistory {

    private Long id;

    private String name;

    private Double buyPrice;

    private Double sellPrice;

    private Double coin;

    private String remark;

}
