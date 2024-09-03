package com.haut.ds.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopIndexImage {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String imageName;
    private String imageUrl;
}
