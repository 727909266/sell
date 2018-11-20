package com.sell.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public ProductInfo findOne(String id) {
        return productInfoDao.findById(id);
    }

    @Override
    public List<ProductInfo> findAll(int page, int size) {
        //PageHelper.startPage(page, size);//之后的第一个查询生效
        //List<ProductInfo> infoList = productInfoDao.findAll();

        //lambda写法
        //Page<ProductInfo> pageProductInfo = PageHelper.startPage(page, size).doSelectPage(() -> productInfoDao.findAll());
        //

        //直接换取pageInfo
        PageInfo<ProductInfo> pageInfoProductInfo = PageHelper.startPage(page, size).doSelectPageInfo(() -> productInfoDao.findAll());
        //
        //返回一个查询语句的数量
        //long total = PageHelper.count(() -> productInfoDao.findByProductStatus(10));
        return pageInfoProductInfo.getList();
    }
}
