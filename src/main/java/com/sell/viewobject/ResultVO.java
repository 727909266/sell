package com.sell.viewobject;

import lombok.Data;

/**
 * Created by huhaoran on 2018/11/21 0021.
 */
@Data
public class ResultVO<T> {
    private int code;
    private String msg;
    private T data;
}
