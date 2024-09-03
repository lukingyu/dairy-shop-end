package com.haut.ds.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录表，用户注册表，
 * id既是主键，也是用户真实id
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
}
