package com.haut.ds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haut.ds.domain.VO.BrandHeat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BrandHeatMapper extends BaseMapper<BrandHeat> {
    @Select("SELECT id,brand_name,heat\n" +
            "FROM\n" +
            "brand as b LEFT JOIN\n" +
            "(SELECT x.brand_id, SUM(x.product_num) as heat\n" +
            "FROM (SELECT o.product_id,o.product_num,p.brand_id\n" +
            "FROM\n" +
            "order_item o LEFT JOIN product p\n" +
            "ON o.product_id=p.id) as x\n" +
            "GROUP BY brand_id\n" +
            "ORDER BY heat DESC) as u on u.brand_id=b.id ORDER BY heat DESC limit #{topNum}")
    List<BrandHeat> getBrandHeatsTop(@Param("topNum") Integer topNum);
}
