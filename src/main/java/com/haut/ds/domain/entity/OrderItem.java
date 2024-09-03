package com.haut.ds.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Integer productNum;
    private Double itemPrice;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private String province;
    private String city;

    //该订单项是否已评价（0是false，代表没有评价。1是true，代表已评价）
    private Integer isEvaluated;

    //后来加的字段，因为前段要显示图片，只能加一个图片属性了，虽然数据库没有，但是要根据productId查出来图片地址并赋值
    @TableField(exist = false)
    private String imageUrl;
}
