package com.haut.ds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haut.ds.domain.VO.CategoryBuySumByBrand;
import com.haut.ds.domain.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select username from user_info where user_id = #{userId}")
    String getUsernameById(@Param("userId") Integer userId);

    @Select("SELECT avg(grade) as avg_grade FROM product_evaluation WHERE user_id=#{userId}")
    Double getAvgGradeOfCurUser(@Param("userId") Integer userId);

    @Select("SELECT c.category_name,c.brand_id,sum(x2.product_num) sum FROM (SELECT * FROM category WHERE brand_id=#{brandId}) c LEFT JOIN (SELECT x1.order_id,x1.product_id,x1.product_num,p.category_id,p.brand_id FROM (SELECT * FROM order_item WHERE order_id in (SELECT id FROM d_order WHERE user_id=#{userId})) x1 LEFT JOIN product p ON x1.product_id=p.id ) x2 ON c.id=x2.category_id GROUP BY c.id")
    List<CategoryBuySumByBrand> getCategoryBuySumByBrandList(@Param("brandId") Integer brandId, @Param("userId") Integer userId);
}
