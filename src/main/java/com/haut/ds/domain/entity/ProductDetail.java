package com.haut.ds.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {
    @TableId
    private Integer id; //主键，和业务无关

    private Integer productId;
    private String productName;
    private Integer brandId;
    private Integer categoryId;
    private Double price;
    private String imageUrl;
    private String introduction; //介绍
    private String ingredient; //配料成分
    private String specification; //规格
    private String shelfLife; //保质期

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
