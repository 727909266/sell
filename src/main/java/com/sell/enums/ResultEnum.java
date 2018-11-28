package com.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(0, "商品不存在");

    private int code;
    private String message;
    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
