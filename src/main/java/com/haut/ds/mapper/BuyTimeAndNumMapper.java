package com.haut.ds.mapper;

import com.haut.ds.domain.VO.BuyTimeAndNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BuyTimeAndNumMapper {
    @Select("SELECT COUNT(*) as buy_pro_num,DATE(create_time) as buy_time FROM (SELECT * FROM order_item WHERE order_id IN (SELECT id FROM d_order WHERE user_id=#{userId})) as x\n" +
            "GROUP BY DATE(create_time) ORDER BY buy_time desc LIMIT #{limit}")
    List<BuyTimeAndNum> getBuyTimeAndNumList(@Param("userId") Integer userId,@Param("limit") Integer limit);
}
