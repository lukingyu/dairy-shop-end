package com.haut.ds.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//商品评级实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvaluation {
    @TableId
    private Integer id;
    //商品id
    private Integer productId;
    //订单id
    private Integer orderId;
    //用户id
    private Integer userId;
    //用户名
    private String username;
    //评分等级
    private Integer grade;
    //评语
    private String comments;
    //更新时间
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

}
