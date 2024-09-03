package com.haut.ds.mapper;

import com.haut.ds.domain.VO.BrandAndBuySum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BrandAndBuySumMapper {
    @Select("SELECT x.*,b.brand_name FROM (SELECT p.brand_id,SUM(product_num) brand_buy_sum FROM (SELECT product_id,product_num FROM order_item WHERE order_id in (SELECT id FROM d_order WHERE user_id=#{userId})) t1 LEFT JOIN product p ON t1.product_id=p.id GROUP BY brand_id) as x LEFT JOIN brand as b on x.brand_id=b.id")
    List<BrandAndBuySum> getBrandAndBuySumList(@Param("userId") Integer userId); //考虑到品牌是有限的，目前不限制数量
}
