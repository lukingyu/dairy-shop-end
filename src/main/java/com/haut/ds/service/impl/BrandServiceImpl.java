package com.haut.ds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haut.ds.domain.VO.BrandHeat;
import com.haut.ds.domain.entity.Brand;
import com.haut.ds.domain.entity.BrandIntroduction;
import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.enums.ResEnum;
import com.haut.ds.mapper.BrandHeatMapper;
import com.haut.ds.mapper.BrandIntroductionMapper;
import com.haut.ds.mapper.BrandMapper;
import com.haut.ds.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    private BrandIntroductionMapper brandIntroductionMapper;
    @Autowired
    private BrandHeatMapper brandHeatMapper;

    @Override
    public MyRes getAllBrands() {
        List<BrandIntroduction> brands = brandIntroductionMapper.selectList(null);
        return MyRes.success(ResEnum.QUERY_SUCCESS, brands);
    }

    @Override
    public MyRes getBrandHeatsTop(Integer topNum) {
        List<BrandHeat> brandHeatsTop = brandHeatMapper.getBrandHeatsTop(topNum);

        return MyRes.success(ResEnum.QUERY_SUCCESS, brandHeatsTop);
    }

    @Override
    public MyRes getAllSimpleBrandList() {
        List<Brand> brandList = list();
        return MyRes.success(ResEnum.QUERY_SUCCESS, brandList);
    }
}
