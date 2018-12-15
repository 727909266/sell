package com.sell.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by huhaoran on 2018/12/16 0016.
 */
public class JsonUtil {
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
