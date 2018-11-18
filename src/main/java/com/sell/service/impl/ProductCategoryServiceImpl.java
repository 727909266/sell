package com.sell.service.impl;

import com.sell.dao.ProductCategoryDao;
import com.sell.model.ProductCategory;
import com.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by huhaoran on 2018/11/18 0018.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findById(int id) {
        return productCategoryDao.findById(id);
    }

    @Override
    public List<ProductCategory> findByCategoryType(int categoryType) {
        return productCategoryDao.findByCategoryType(categoryType);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public void saveModel(ProductCategory productCategory) {
        productCategoryDao.saveModel(productCategory);
    }

    @Override
    public int updateCategoryType(int categoryType, int id) {
        return productCategoryDao.updateCategoryType(categoryType, id);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes) {
        return productCategoryDao.findByCategoryTypeIn(Arrays.asList(1, 2, 3));
    }
}
