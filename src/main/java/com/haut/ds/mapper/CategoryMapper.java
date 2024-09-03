package com.haut.ds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haut.ds.domain.VO.DistinctNameAndIds;
import com.haut.ds.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("select category_name from category where id = #{categoryId}")
    String getCategoryName(@Param("categoryId") Integer categoryId);

    @Select("SELECT category_name, GROUP_CONCAT(DISTINCT id ORDER BY id SEPARATOR ',') AS ids_str FROM category GROUP BY category_name")
    List<DistinctNameAndIds> getDistinctNameAndIdsList(); //获取不重复的分类名,和对应的idsStr， 为了用户根据分类名筛选商品
}
