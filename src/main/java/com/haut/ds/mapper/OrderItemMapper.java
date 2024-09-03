package com.haut.ds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haut.ds.domain.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    List<Integer> getFavoriteCategoryIds(@Param("orderIds") List<Integer> orderIds);
}
