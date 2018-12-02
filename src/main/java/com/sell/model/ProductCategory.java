package com.sell.model;

import lombok.*;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.sql.Timestamp;

//@Entity //说明这个class是实体类，并且使用默认的orm规则，即class名即数据库表中表名，class字段名即表中的字段名. 如果想改变这种默认的orm规则，就要使用@Table来改变class名与数据库中表名的映射规则，@Column来改变class中字段名与db中表的字段名的映射规则
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductCategory {

    private Integer categoryId; //类目id
    @NonNull
    private String categoryName; //类目名字
    @NonNull
    private Integer categoryType; //类目编号

    private Date createTime;

    private Date updateTime;

}
