package com.haut.ds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haut.ds.domain.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLoginMapper extends BaseMapper<UserLogin> {
}
