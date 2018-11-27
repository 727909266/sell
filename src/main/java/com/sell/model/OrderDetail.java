package com.sell.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OrderDetail {

    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;//单价
    private Integer productQuantity;//数量
    private String productIcon; //小图
    private Timestamp createTime;
    private Timestamp updateTime;
    
}
