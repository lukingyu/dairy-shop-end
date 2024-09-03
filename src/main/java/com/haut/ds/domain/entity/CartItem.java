package com.haut.ds.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Integer id;
    private Integer userId;
    private String productName;
    private Integer productNum;
    private Integer productId;
    private Double totalPrice;

}
