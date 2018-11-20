package com.sell.controller;

import com.sell.model.ProductInfo;
import com.sell.viewobject.ProductInfoVO;
import com.sell.viewobject.ProductVO;
import com.sell.viewobject.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by huhaoran on 2018/11/21 0021.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

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

}
