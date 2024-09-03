package com.haut.ds.controller;

import com.haut.ds.domain.DTO.OrderDTO;
import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.domain.entity.ProductEvaluation;
import com.haut.ds.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/payGenOrderAndOrderItem")
    public MyRes payGenOrderAndOrderItem(@RequestBody OrderDTO orderDTO){
        System.out.println(orderDTO);
        return orderService.payGenOrderAndOrderItem(orderDTO);
    }

    @GetMapping("/getAllMyOrderVOs/{userId}")
    public MyRes getAllMyOrderVOs(@PathVariable("userId") Integer userId){
        return orderService.getAllMyOrderVOs(userId);
    }

    @PostMapping("/delOrder/{orderId}")
    public MyRes delOrder(@PathVariable("orderId") Integer orderId){
        return orderService.delOrder(orderId);
    }

    @PostMapping("/evaluateProduct")
    public MyRes evaluateProduct(@RequestBody ProductEvaluation productEvaluation){
        return orderService.evaluateProduct(productEvaluation);
    }
}
