package com.haut.ds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haut.ds.domain.entity.ProductDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailMapper extends BaseMapper<ProductDetail> {
}
