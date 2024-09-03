package com.haut.ds.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "d_order") //数据库的表名不能叫做order，因为order是mysql的关键字，用mp拼接sql的时候会有语法错误。请一定要注意。
public class Order {
    private Integer id;
    private Integer userId;
    //这个要随机生成的。
    private String orderNo;
    private Double allPrice;

    @TableField(fill = FieldFill.INSERT)

    private LocalDateTime createTime;

    private String province;
    private String city;
}
