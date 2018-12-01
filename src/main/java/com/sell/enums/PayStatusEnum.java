package com.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private int code;
    private String message;
    PayStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
