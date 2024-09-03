package com.haut.ds.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemVO {
    private Integer productId;
    private String productName;
    private Integer productNum;
    private Double itemPrice;

    private Integer isEvaluated;

    private String imageUrl;

}
