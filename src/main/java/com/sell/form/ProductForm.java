package com.sell.form;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductForm {
    private String productId; //随机字符串主键
    private String productName;//名字
    private BigDecimal productPrice;//单价
    private Integer productStock;//库存
    private String productDescription;//描述
    private String productIcon;//小图
    private Integer categoryType;//类目编号
    private Date createTime;
    private Date updateTime;
}
