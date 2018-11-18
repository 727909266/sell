package com.sell.service.impl;

import com.sell.dao.ProductInfoDao;
import com.sell.model.ProductInfo;
import com.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huhaoran on 2018/11/18 0018.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productInfoDao;
    @Override
    public List<ProductInfo> findByProductStatus(int status) {
        return productInfoDao.findByProductStatus(status);
    }

    @Override
    public void saveModel(ProductInfo productInfo) {
        productInfoDao.saveModel(productInfo);
    }
}
