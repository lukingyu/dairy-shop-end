package com.haut.ds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haut.ds.domain.DTO.SelectInfoListHasUserIdDTO;
import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.domain.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface ProductService extends IService<Product> {
    MyRes getProductsByKeyword(String keyword);

    MyRes getAllProducts();

    MyRes getSelectedProductInfos(Map<Integer, Integer> selectedProductMap);

    MyRes addToMyCart(SelectInfoListHasUserIdDTO dto);

    MyRes getAllBrands();

    MyRes getAllCategories();

    MyRes getProductDetail(Integer productId);

    MyRes getFavoriteProducts(Integer userId);
}
