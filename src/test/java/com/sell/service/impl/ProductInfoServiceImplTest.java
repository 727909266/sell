package com.sell.service.impl;

import com.sell.model.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by huhaoran on 2018/11/18 0018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl productInfoServiceImpl;
    @Test
    public void findByProductStatus() throws Exception {
        Assert.assertNotEquals(-1, productInfoServiceImpl.findByProductStatus(1).size());
    }

    @Test
    public void saveModel() throws Exception {
        ProductInfo productInfo = new ProductInfo("123", "牛排", new BigDecimal(3.2), 5, "独一无二的肉", "http://xxxx.jpg", 0, 10);
        productInfoServiceImpl.saveModel(productInfo);
    }
}