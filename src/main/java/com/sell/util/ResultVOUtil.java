package com.sell.util;

import com.sell.viewobject.ResultVO;

/**
 * Created by huhaoran on 2018/11/23 0023.
 */
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        return new ResultVO(0, "成功", object);
    }
    public static ResultVO success() {
        return success(null);
    }
    public static ResultVO fail(int code, String msg) {
        return new ResultVO(code, msg);
    }
}
