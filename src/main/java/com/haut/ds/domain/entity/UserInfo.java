package com.haut.ds.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户详细信息表。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    // 主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 用户真实id
    private Integer userId;
    private String username;
    private Integer age;
    private String phone;
}
