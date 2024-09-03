package com.haut.ds.domain.VO;

public class BrandHeat { //品牌热度类
    private String brandName;
    private Integer heat;

    public BrandHeat() {
    }

    public BrandHeat(String brandName, Integer heat) {
        this.brandName = brandName;
        if (heat==null){
            heat = 0;
        }
        this.heat = heat * 100;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        if (heat==null) heat=0;
        this.heat = heat * 100;
    }
}
