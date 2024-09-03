package com.haut.ds.domain.VO;

import com.haut.ds.domain.entity.ProductEvaluation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailVO {
    private Integer id; //主键，和业务无关

    private Integer productId;
    private String productName;
    private Integer brandId;
    private Integer categoryId;

    //后期添加的
    private String categoryName;

    private Double price;
    private String imageUrl;
    private String introduction; //介绍
    private String ingredient; //配料成分
    private String specification; //规格
    private String shelfLife; //保质期

    private LocalDateTime createTime;

    private Integer totalSalesVolume; //商品总销量，表里没有

    private ProductEvaluation latestProductEvaluation; //最新的一条评价，作为首先展示

    private List<ProductEvaluation> productEvaluations; //该商品的所有评价
}
