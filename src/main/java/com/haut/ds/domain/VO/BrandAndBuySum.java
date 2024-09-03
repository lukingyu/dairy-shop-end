package com.haut.ds.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandAndBuySum { //个人，品牌购买数量（相当于品牌喜爱度）

    private Integer brandId;
    private Integer brandBuySum; //品牌购买总量
    private String brandName;
}
