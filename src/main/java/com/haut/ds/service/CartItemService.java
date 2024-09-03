package com.haut.ds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haut.ds.domain.DTO.CartItemDTO;
import com.haut.ds.domain.entity.CartItem;
import com.haut.ds.domain.entity.MyRes;

public interface CartItemService extends IService<CartItem> {
    MyRes getAllMyCartItems(Integer userId);

    MyRes reduceCartItemProductNum(CartItemDTO cartItemDTO);

    MyRes deleteCartItemProduct(CartItemDTO cartItemDTO);

    MyRes plusCartItemProductNum(CartItemDTO cartItemDTO);

    MyRes clearMyCart(CartItemDTO cartItemDTO);
}
