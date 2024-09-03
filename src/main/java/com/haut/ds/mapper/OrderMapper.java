package com.haut.ds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haut.ds.domain.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
