package com.jluzh.sell.dataobject.mapper;

import com.jluzh.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ProductCategoryMapper {
    @Select("select * from product_category where category_type = #{categoryType}")
    @Results(
            //把字段名映射成变量
            {@Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")}
    )
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name = #{categoryName}")
    @Results(
            //把字段名映射成变量
            {@Result(column = "category_id", property = "categoryId"),
                    @Result(column = "category_name", property = "categoryName"),
                    @Result(column = "category_type", property = "categoryType")}
    )
    List<ProductCategory> findByCategoryName(String categoryName);

    //对应map中的key
    @Insert("insert into product_category(category_name, category_type) values (#{categoryName,jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into product_category(category_name, category_type) values (#{categoryName,jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    //传多个参数时，使用注解@Parma
    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);


    //使用xml形式
    ProductCategory selectByCategoryType(Integer CategoryType);
}
