package com.haut.ds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haut.ds.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    @Select("select id from d_order where user_id = #{userId}")
    List<Integer> getOrderIdsByUserId(@Param("userId") Integer userId);
}
