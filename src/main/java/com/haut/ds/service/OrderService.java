package com.haut.ds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haut.ds.domain.DTO.OrderDTO;
import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.domain.entity.Order;
import com.haut.ds.domain.entity.ProductEvaluation;

public interface OrderService extends IService<Order> {
    MyRes payGenOrderAndOrderItem(OrderDTO orderDTO);

    MyRes getAllMyOrderVOs(Integer userId);

    MyRes evaluateProduct(ProductEvaluation productEvaluation);

    MyRes delOrder(Integer orderId);
}
