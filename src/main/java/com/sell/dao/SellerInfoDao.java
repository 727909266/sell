package com.sell.dao;

import com.sell.model.OrderDetail;
import com.sell.model.SellerInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SellerInfoDao {
    String TABLE_NAME = "seller_info";
    String INSERT_FIELD = "seller_id, username, password, openid";
    String INSERT_VALUE = "(#{id}, #{username}, #{password}, #{openid})";

    @Select({"select * from ", TABLE_NAME, " where openid = #{openId}"})
    SellerInfo findByOpenId(@Param("openId") String openId);

    @Select({"select * from ", TABLE_NAME, " where seller_id = #{sellerId}"})
    SellerInfo findById(@Param("sellerId") String sellerId);

    @Insert({"insert into ", TABLE_NAME, "(",INSERT_FIELD, ") values", INSERT_VALUE})
    void saveModel(SellerInfo sellerInfo);
}
