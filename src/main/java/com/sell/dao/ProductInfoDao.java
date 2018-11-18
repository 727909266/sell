package com.sell.dao;

import com.sell.model.ProductCategory;
import com.sell.model.ProductInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by huhaoran on 2018/11/18 0018.
 */
@Mapper
public interface ProductInfoDao {
    String TABLE_NAME = "product_info";
    String INSER_FIELD = "product_id, product_name, product_price, product_stock, product_description, product_icon, product_status, category_type";

    @Select({"select * from ",TABLE_NAME, " where product_status = #{status}"})
    List<ProductInfo> findByProductStatus(int status);

    @Select({"select * from ",TABLE_NAME, " where product_id = #{id}"})
    ProductInfo findById(String id);

    @Insert({"insert into ", TABLE_NAME," (", INSER_FIELD,") values(#{productId},#{productName},#{productPrice},#{productStock},#{productDescription},#{productIcon},#{productStatus},#{categoryType})"})
    void saveModel(ProductInfo productInfo);
}
