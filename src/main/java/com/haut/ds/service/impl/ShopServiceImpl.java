package com.haut.ds.service.impl;

import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.domain.entity.ShopIndexImage;
import com.haut.ds.enums.ResEnum;
import com.haut.ds.mapper.ShopIndexImageMapper;
import com.haut.ds.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "shopService")
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopIndexImageMapper shopIndexImageMapper;
    @Override
    public MyRes getShopIndexImages() {
        List<ShopIndexImage> shopIndexImages = shopIndexImageMapper.selectList(null);
        return MyRes.success(ResEnum.QUERY_SUCCESS, shopIndexImages);
    }
}
