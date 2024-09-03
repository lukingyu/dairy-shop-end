package com.haut.ds.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

public class CategoryBuySumByBrand {  //只针对于当前用户

    private String categoryName; //分类名
    private Integer brandId; //该分类的品牌ID
    private Integer sum = 0; //该分类的购买总数量

    public CategoryBuySumByBrand() {
    }

    public CategoryBuySumByBrand(String categoryName, Integer brandId, Integer sum) {
        this.categoryName = categoryName;
        this.brandId = brandId;
        if (sum != null){
            this.sum = sum;
        }
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        //如果为空，则设置总数为0
        if (sum == null){
            this.sum = 0;
        }else {
            this.sum = sum;
        }
    }
}
