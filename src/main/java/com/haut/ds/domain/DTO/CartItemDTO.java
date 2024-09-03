package com.haut.ds.domain.DTO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private Integer userId;
    private Integer productId;
    @TableField(exist = false)
    private Double unitPrice;
}
