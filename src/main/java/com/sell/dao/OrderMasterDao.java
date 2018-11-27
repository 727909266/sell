package com.sell.dao;

import com.sell.model.OrderMaster;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface OrderMasterDao {
    String TABLE_NAME = "order_master";
    String INSERT_FIELD = "order_id, buyer_name, buyer_phone, buyer_address, buyer_openid, order_amount, order_status, pay_status";
    String INSERT_VALUE = "(#{orderId},#{buyerName},#{buyerPhone},#{buyerAddress},#{buyerOpenid},#{orderAmount},#{orderStatus},#{payStatus})";

    @Select({"select * from ", TABLE_NAME, " where buyer_openid = #{buyerOpenid}"})
    List<OrderMaster> findByBuyerOpenid(@Param("buyerOpenid") String buyerOpenid);

    @Select({"select * from ", TABLE_NAME, " where order_id = #{orderId}"})
    OrderMaster findById(@Param("orderId") String orderId);

    @Insert({"insert into ", TABLE_NAME, "(",INSERT_FIELD, ") values", INSERT_VALUE})
    void saveModel(OrderMaster orderMaster);

}
