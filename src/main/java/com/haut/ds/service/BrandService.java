package com.haut.ds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haut.ds.domain.entity.Brand;
import com.haut.ds.domain.entity.MyRes;

public interface BrandService extends IService<Brand> {
    MyRes getAllBrands();

    MyRes getBrandHeatsTop(Integer topNum);

    MyRes getAllSimpleBrandList();
}
