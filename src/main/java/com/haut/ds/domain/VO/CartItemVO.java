package com.haut.ds.domain.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemVO {
    private Integer id;
    private Integer userId;
    private String productName;
    private Integer productNum;
    private Integer productId;
    private Double totalPrice;
    //商品单价，数据库表中没有
    @TableField(exist = false)
    private Double unitPrice;
}
