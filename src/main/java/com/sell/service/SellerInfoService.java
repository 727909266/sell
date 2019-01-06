package com.sell.service;

import com.sell.model.SellerInfo;

import java.util.List;

public interface SellerInfoService {
    SellerInfo findByOpenId(String openid);
}
