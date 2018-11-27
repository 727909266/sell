package com.sell.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SellerInfo {
    private String id;
    private String username;
    private String password;
    private String openid;
    //创建时间
    private Timestamp createTime;

    //修改时间
    private Timestamp updateTime;
}
