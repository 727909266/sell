package com.sell.dao;

import com.sell.model.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductCategoryDao {
    //update/delete，返回值是：更新或删除的行数
    //insert，返回值是：新插入行的主键,赋值到实例id
    String TABLE_NAME = "product_category";
    String INSERT_FIELD = "category_name, category_type";
    String INSERT_VALUE = "(#{categoryName},#{categoryType})";

    @Select({"select * from ", TABLE_NAME, " where category_id = #{aaa}"})
    ProductCategory findById(@Param("aaa") int id);

    @Select({"select * from ", TABLE_NAME})
    List<ProductCategory> findAll();

    @Select({"select * from ", TABLE_NAME, " where category_type = #{categoryType}"})
    List<ProductCategory> findByCategoryType(int categoryType);

    //@Select({"select * from ", TABLE_NAME, " where category_type in (#{categoryTypes}"})
    List<ProductCategory> findByCategoryTypeIn(@Param("categoryTypes") List<Integer> categoryTypes);

    @Insert({"insert into ", TABLE_NAME,"(" ,INSERT_FIELD ,") values", INSERT_VALUE})
    @Options(useGeneratedKeys = true, keyColumn = "category_id", keyProperty = "categoryId")
    void saveModel(ProductCategory productCategory);

    @Update({"update ", TABLE_NAME, " set category_type = #{categoryType} where category_id = #{id}"})
    int updateCategoryType(@Param("categoryType") int categoryType, @Param("id") int id);
}
