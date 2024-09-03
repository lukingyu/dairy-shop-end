package com.haut.ds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haut.ds.domain.entity.ProductEvaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductEvaluationMapper extends BaseMapper<ProductEvaluation> {

    @Select("select * from product_evaluation where product_id = #{productId} order by create_time desc limit 1")
    @ResultType(ProductEvaluation.class)
    ProductEvaluation getLatestProductEvaluation(@Param("productId") Integer productId);
}
