package com.haut.ds.controller;

import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/getAllBrands")
    public MyRes getAllBrands(){
        return brandService.getAllBrands();
    }

    @GetMapping("/getBrandHeatsTop/{topNum}")
    public MyRes getBrandHeatsTop(@PathVariable("topNum") Integer topNum){
        return brandService.getBrandHeatsTop(topNum);
    }

    @GetMapping("/getAllSimpleBrandList")
    public MyRes getAllSimpleBrandList(){
        return brandService.getAllSimpleBrandList();
    }

}
