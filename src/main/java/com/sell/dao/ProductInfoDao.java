package com.sell.dao;

import com.sell.model.ProductCategory;
import com.sell.model.ProductInfo;
import com.sell.util.HandleInsertFieldUtil;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by huhaoran on 2018/11/18 0018.
 */
@Mapper
public interface ProductInfoDao {
    String TABLE_NAME = "product_info";
    String INSER_FIELD = "product_id, product_name, product_price, product_stock, product_description, product_icon, product_status, category_type";
    String INSERT_VALUE = "(#{productId},#{productName},#{productPrice},#{productStock},#{productDescription},#{productIcon},#{productStatus},#{categoryType})";

    @Select({"select * from ",TABLE_NAME, " where product_status = #{status}"})
    List<ProductInfo> findByProductStatus(int status);

    @Select({"select * from ",TABLE_NAME, " where product_id = #{id}"})
    ProductInfo findById(String id);

    @Select({"select * from ",TABLE_NAME})
    List<ProductInfo> findAll();

    @Insert({"insert into ", TABLE_NAME," (", INSER_FIELD,") values", INSERT_VALUE})
    void saveModel(ProductInfo productInfo);

    @Update({"update ",TABLE_NAME, " set product_stock = #{productStock} where product_id = #{productId}"})
    int updateModel(@Param("productStock") int productStock , @Param("productId")String productId);
}
