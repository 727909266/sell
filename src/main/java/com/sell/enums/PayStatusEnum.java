package com.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum{
    UNPAID(1, "未支付"),
    PAID(0, "已支付");

    private int code;
    private String message;
    PayStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
