package com.haut.ds.controller;

import com.haut.ds.domain.DTO.CartItemDTO;
import com.haut.ds.domain.entity.CartItem;
import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/getAllMyCartItems/{userId}")
    public MyRes getAllMyCartItems(@PathVariable("userId") Integer userId){
        return cartItemService.getAllMyCartItems(userId);
    }

    @PostMapping("/reduceCartItemProductNum")
    public MyRes reduceCartItemProductNum(@RequestBody CartItemDTO cartItemDTO){
        return cartItemService.reduceCartItemProductNum(cartItemDTO);
    }

    @PostMapping("/deleteCartItemProduct")
    public MyRes deleteCartItemProduct(@RequestBody CartItemDTO cartItemDTO){
        return cartItemService.deleteCartItemProduct(cartItemDTO);
    }

    @PostMapping("/plusCartItemProductNum")
    public MyRes plusCartItemProductNum(@RequestBody CartItemDTO cartItemDTO){
        return cartItemService.plusCartItemProductNum(cartItemDTO);
    }

    @PostMapping("/clearMyCart")
    public MyRes clearMyCart(@RequestBody CartItemDTO cartItemDTO){
        return cartItemService.clearMyCart(cartItemDTO);
    }
}
