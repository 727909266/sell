package com.huhaoran.dao;

import com.huhaoran.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductCategoryDao {
    //update/delete，返回值是：更新或删除的行数
    //insert，返回值是：新插入行的主键
    String TABLE_NAME = "product_category";
    String INSERT_FIELD = "category_name, category_type";

    @Select({"select * from ", TABLE_NAME, " where category_id = #{a}"})
    ProductCategory findById(@Param("a") int id);

    //@Insert({"insert into ", TABLE_NAME,"(" ,INSERT_FIELD ,") values(#{categoryName},#{categoryType})"})
    int saveModel(ProductCategory productCategory);

    @Update({"update ", TABLE_NAME, " set category_type = #{categoryType} where category_id = #{id}"})
    int updateCategoryType(@Param("categoryType") int categoryType, @Param("id") int id);
}
