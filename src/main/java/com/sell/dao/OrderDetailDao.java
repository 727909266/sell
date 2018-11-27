package com.sell.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailDao {
    String TABLE_NAME = "order_detail";
}
