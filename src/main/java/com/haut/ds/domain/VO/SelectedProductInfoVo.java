package com.haut.ds.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedProductInfoVo {
    private Integer productId;
    private String productName;
    private Integer num;
    private Double totalPrice;
    private String imageUrl;
}
