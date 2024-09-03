package com.haut.ds.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String productName;
    private Integer categoryId;
    private Integer brandId;
    private Double price;
    private String imageUrl;

    //为了比较两个对象是否相同，根据属性值来判断，则一定要重写这两个方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(productName, product.productName) && Objects.equals(categoryId, product.categoryId) && Objects.equals(brandId, product.brandId) && Objects.equals(price, product.price) && Objects.equals(imageUrl, product.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, categoryId, brandId, price, imageUrl);
    }
}
