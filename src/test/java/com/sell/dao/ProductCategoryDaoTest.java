package com.sell.dao;

import com.sell.model.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = productCategoryDao.findById(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("红烧肉");
        productCategory.setCategoryType(3);
        productCategoryDao.saveModel(productCategory);
        System.out.println(productCategory.getCategoryId());
        Assert.assertNotEquals(Integer.valueOf(0), productCategory.getCategoryId());
    }

    @Test
    public void updateTest() {
        System.out.println(productCategoryDao.updateCategoryType(10, 1));
    }
}