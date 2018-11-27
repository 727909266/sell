package com.sell.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMasterDao {
    String TABLE_NAME = "order_master";
}
