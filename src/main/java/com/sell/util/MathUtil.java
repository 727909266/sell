package com.sell.util;

/**
 * Created by huhaoran on 2018/12/18 0018.
 */
public class MathUtil {
    private static final Double moneyRange = 0.01;
    public static Boolean equals(Double a1, Double a2) {
        if(Math.abs(a1 - a2) < moneyRange) {
            return true;
        } else {
            return false;
        }
    }
}
