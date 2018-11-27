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
    String INSERT_FIELD = "id, username, password, openid";
    String INSERT_VALUE = "(#{id}, #{username}, #{password}, #{openid})";

    @Select({"select * from ", TABLE_NAME, " where order_id = #{orderId}"})
    List<SellerInfo> findByOrderId(@Param("orderId") String orderId);

    @Select({"select * from ", TABLE_NAME, " where detail_id = #{detailId}"})
    SellerInfo findById(@Param("detailId") String detailId);

    @Insert({"insert into ", TABLE_NAME, "(",INSERT_FIELD, ") values", INSERT_VALUE})
    void saveModel(SellerInfo sellerInfo);
}
