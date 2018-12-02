package com.sell.dao;

import com.sell.model.OrderMaster;
import org.apache.ibatis.annotations.*;
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

    @Update({"update ", TABLE_NAME, " set order_status = #{orderStatus} where order_id = #{id}"})
    int updateOrderStatus(@Param("orderStatus") int orderStatus, @Param("id") String id);

    @Update({"update ", TABLE_NAME, " set pay_status = #{payStatus} where order_id = #{id}"})
    int updatePayStatus(@Param("payStatus") int payStatus, @Param("id") String id);

}
