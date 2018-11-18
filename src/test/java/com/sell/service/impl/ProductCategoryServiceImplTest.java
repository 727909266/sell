package com.sell.service.impl;

import com.sell.model.ProductCategory;
import com.sell.service.ProductCategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by huhaoran on 2018/11/18 0018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Autowired
    private
    ProductCategoryServiceImpl productCategoryServiceImpl;

    @Test
    public void findById() throws Exception {
        Assert.assertEquals(Integer.valueOf(1), productCategoryServiceImpl.findById(1).getCategoryId());
    }

    @Test
    public void findByCategoryType() throws Exception {
        productCategoryServiceImpl.findByCategoryType(10).forEach(System.out::println);
        Assert.assertNotEquals(0, productCategoryServiceImpl.findByCategoryType(10).size());
    }

    @Test
    public void findAll() throws Exception {
        Assert.assertNotEquals(0, productCategoryServiceImpl.findAll().size());
    }

    @Test
    public void saveModel() throws Exception {
        ProductCategory productCategory = new ProductCategory("汉堡王", 10);
        productCategoryServiceImpl.saveModel(productCategory);
        Assert.assertNotEquals(productCategory.getCategoryId(), Integer.valueOf(0));


    }

    @Test
    public void updateCategoryType() throws Exception {
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {

    }

}