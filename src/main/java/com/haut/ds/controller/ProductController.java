package com.haut.ds.controller;

import com.haut.ds.domain.DTO.SelectInfoListHasUserIdDTO;
import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProductsByKeyword/{keyword}")
    public MyRes getProductsByKeyword(@PathVariable("keyword") String keyword){
        return productService.getProductsByKeyword(keyword);
    }

    @GetMapping("/getAllProducts")
    public MyRes getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/getAllBrands")
    public MyRes getAllBrands(){
        return productService.getAllBrands();
    }
    @GetMapping("/getAllCategories")
    public MyRes getAllCategories(){
        return productService.getAllCategories();
    }

    @PostMapping("/getSelectedProductInfos")
    public MyRes getSelectedProductInfos(@RequestBody Map<Integer, Integer> selectedProductMap){
        //参数传过来的Map集合是<商品id，商品数量>
        return productService.getSelectedProductInfos(selectedProductMap);
    }

    @PostMapping("/addToMyCart")
    public MyRes addToMyCart(@RequestBody SelectInfoListHasUserIdDTO dto){
        return productService.addToMyCart(dto);
    }

    @GetMapping("/getProductDetail/{productId}")
    public MyRes getProductDetail(@PathVariable("productId") Integer productId){
        return productService.getProductDetail(productId);
    }

    @GetMapping("/getFavoriteProducts/{userId}")
    public MyRes getFavoriteProducts(@PathVariable("userId") Integer userId){
        return productService.getFavoriteProducts(userId);
    }

}
