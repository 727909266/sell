package com.sell.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class OrderDetail {//订单详情表

    private String detailId;
    //订单id
    private String orderId;
    //商品id
    private String productId;
    //商品名称
    private String productName;
    //单价
    private BigDecimal productPrice;
    //数量
    private Integer productQuantity;
    //小图
    private String productIcon;
    private Date createTime;
    private Date updateTime;
    
}
