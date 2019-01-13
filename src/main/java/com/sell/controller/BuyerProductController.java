package com.sell.controller;

import com.sell.model.ProductCategory;
import com.sell.model.ProductInfo;
import com.sell.service.ProductCategoryService;
import com.sell.service.ProductInfoService;
import com.sell.util.ResultVOUtil;
import com.sell.viewobject.ProductInfoVO;
import com.sell.viewobject.ProductVO;
import com.sell.viewobject.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by huhaoran on 2018/11/21 0021.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/test")
    public ResultVO test() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");

        ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();
        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));

        resultVO.setData(Arrays.asList(productVO));

        return resultVO;
    }

/*
    @Cacheable(cacheNames = "product", key = "#sellerId", condition = "#sellerId.length() > 3", unless = "#result.getCode() != 0")
    //ResultVO中code = 0则进行缓存
    //动态生成key，SPEL表达式
    //如果条件成立，对结果进行缓存
    public ResultVO list(String sellerId) {

    }
*/

    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "123")
    public ResultVO list() {
        //1.查询所有上架商品
        List<ProductInfo> productInfos = productInfoService.findAll(0, 100);
        System.out.println(productInfos.size());
        //2.查询类目(一次查询)
        List<Integer> categoryTypeList = productInfos.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> productCategories = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        System.out.println(categoryTypeList.get(0));
        System.out.println(productCategories);
        //3.数据拼接
        List<ProductVO> productVOS = productCategories.stream().map(productCategory -> {
            ProductVO productVO= new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfosByCategoryType =
                    productInfos.stream().filter(productInfo -> Objects.equals(productInfo.getCategoryType(), productCategory.getCategoryType()))
                            .map(productInfo -> {
                                ProductInfoVO productInfoVO =  new ProductInfoVO();
                                BeanUtils.copyProperties(productInfo, productInfoVO);
                                return productInfoVO;
                            })
                            .collect(Collectors.toList());
            productVO.setProductInfoVOList(productInfosByCategoryType);
            return productVO;
        }).collect(Collectors.toList());

        return ResultVOUtil.success(productVOS);
    }



}
