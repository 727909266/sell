package com.sell.dao;

import com.sell.model.OrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDetailDao {
    String TABLE_NAME = "order_detail";
    String INSERT_FIELD = "detail_id, order_id, product_id, product_name, product_price, product_quantity, product_icon";
    String INSERT_VALUE = "(#{detailId}, #{orderId}, #{productId}, #{productName}, #{productPrice}, #{productQuantity}, #{productIcon})";

    @Select({"select * from ", TABLE_NAME, " where order_id = #{orderId}"})
    List<OrderDetail> findByOrderId(@Param("orderId") String orderId);

    @Select({"select * from ", TABLE_NAME, " where detail_id = #{detailId}"})
    OrderDetail findById(@Param("detailId") String detailId);

    @Insert({"insert into ", TABLE_NAME, "(",INSERT_FIELD, ") values", INSERT_VALUE})
    void saveModel(OrderDetail orderDetail);

}
