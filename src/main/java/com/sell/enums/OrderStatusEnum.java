package com.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    VALID(0, "有效"),
    INVALID(1, "失效");

    private int code;
    private String message;
    OrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
