package com.sell.model;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class SellerInfo {
    private String id;
    private String username;
    private String password;
    private String openid;
    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}
