package com.sell.form;

import lombok.Data;
import lombok.NonNull;

import java.sql.Date;

@Data
public class CategoryForm {
    private Integer categoryId; //类目id
    private String categoryName; //类目名字
    private Integer categoryType; //类目编号

    private Date createTime;

    private Date updateTime;
}
