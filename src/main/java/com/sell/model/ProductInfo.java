package com.sell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sell.enums.ProductStatusEnum;
import com.sell.util.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by huhaoran on 2018/11/18 0018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {

    private String productId; //随机字符串主键
    private String productName;//名字
    private BigDecimal productPrice;//单价
    private Integer productStock;//库存
    private String productDescription;//描述
    private String productIcon;//小图
    private Integer productStatus = ProductStatusEnum.UP.getCode();//状态 0正常，1下架
    private Integer categoryType;//类目编号
    private Date createTime;
    private Date updateTime;
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

}
