package com.sell.service;

import com.sell.model.ProductInfo;

import java.util.List;

/**
 * Created by huhaoran on 2018/11/18 0018.
 */
public interface ProductInfoService {
    List<ProductInfo> findByProductStatus(int status);
    void saveModel(ProductInfo productInfo);
    List<ProductInfo> findAll(int page, int size);
    ProductInfo findOne(String id);
    //查询所有在架商品列表
    //加库存
    //减库存
}
