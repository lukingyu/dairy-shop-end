package com.haut.ds.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyCartItemMapValueDTO {
    private Integer userId;
    private String productName;
    private Integer productNum;
    private Double totalPrice;
}
