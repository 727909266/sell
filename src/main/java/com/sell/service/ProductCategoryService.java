package com.sell.service;

import com.sell.model.ProductCategory;

import java.util.List;

/**
 * Created by huhaoran on 2018/11/18 0018.
 */
public interface ProductCategoryService {
    ProductCategory findById(int id);
    List<ProductCategory> findByCategoryType(int categoryType);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);
    void saveModel(ProductCategory productCategory);
    int updateCategoryType(int categoryType,  int id);
}
