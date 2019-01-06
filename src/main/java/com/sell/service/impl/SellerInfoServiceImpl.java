package com.sell.service.impl;

import com.sell.dao.SellerInfoDao;
import com.sell.model.SellerInfo;
import com.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findByOpenId(String openid) {
        return sellerInfoDao.findByOpenId(openid);
    }

}
