package com.sell.enums;

import lombok.Getter;

/**
 * 商品状态
 * Created by huhaoran on 2018/11/21 0021.
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{

    UP(0, "在架"),
    DOWN(1, "下架");

    private int code;
    private String message;
    ProductStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
