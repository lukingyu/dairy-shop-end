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
public class BrandIntroduction {
    private Integer id;
    private Integer brandId;
    private String brandName;
    private String logoUrl;
    private String introduction;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
