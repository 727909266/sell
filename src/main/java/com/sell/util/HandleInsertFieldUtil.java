package com.sell.util;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.stream.Collectors;


abstract public class HandleInsertFieldUtil {
    public static String getInsertValue(String insertField) {
        StringBuilder insertValue = new StringBuilder('(');//多线程使用StringBuffer
        List<String> fields = Splitter.on(',').splitToList(insertField.trim());

        fields.forEach(field -> {
            int index = field.indexOf('_');
            char ch = field.charAt(index + 1);
            ch -= 32;
            String handleField = (field.substring(0, index) + ch).concat(field.substring(index + 2, field.length()));
            String newField = new StringBuilder("#{").append(handleField).append("},").toString();
            insertValue.append(newField);
        });

        return insertValue.deleteCharAt(insertValue.length() -1).append(')').toString();

    }
}
