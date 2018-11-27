package com.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    PAID(0, "未支付"),
    UNPAID(1, "已支付");

    private int code;
    private String message;
    PayStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
