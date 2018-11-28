package com.sell.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式： 时间 + 随机数
     * @return
     */
    public static String getUniqueKey() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        Integer number = threadLocalRandom.nextInt(900000) + 100000;//6位随机数
        return System.currentTimeMillis() + String.valueOf(number);

    }
}
