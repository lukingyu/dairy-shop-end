package com.haut.ds.controller;

import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.mapper.ShopIndexImageMapper;
import com.haut.ds.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shopIndexImages")
    public MyRes getShopIndexImages(){
        return shopService.getShopIndexImages();
    }
}
