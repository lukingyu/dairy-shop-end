package com.haut.ds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haut.ds.domain.DTO.MyCartItemMapValueDTO;
import com.haut.ds.domain.DTO.OrderDTO;
import com.haut.ds.domain.VO.OrderItemVO;
import com.haut.ds.domain.VO.OrderVO;
import com.haut.ds.domain.entity.*;
import com.haut.ds.enums.ResEnum;
import com.haut.ds.mapper.*;
import com.haut.ds.service.OrderService;
import com.haut.ds.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductEvaluationMapper productEvaluationMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public MyRes payGenOrderAndOrderItem(OrderDTO orderDTO) {
        //先生成订单Order
        //随机生成一个订单no，字符串类型
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date()); //根据上面的格式，生成一个时间戳字符串
        int randomSixNumber = new Random().nextInt(1000000); //利用Random对象生成一个随机六位数
        String numberStr = String.format("%06d", randomSixNumber); //将随机生成的六位数字格式化
        String orderNo = timestamp + numberStr; //拼接生成订单号

        Order order = new Order(null, orderDTO.getUserId(), orderNo, orderDTO.getNeedPayPrice(), null, orderDTO.getProvince(), orderDTO.getCity());
        save(order);

        //再生成一个个的订单项
        List<OrderItem> orderItems = new ArrayList<>();
        Map<Integer, MyCartItemMapValueDTO> myCartItemMap = orderDTO.getMyCartItemMap();

        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getOrderNo, orderNo);
        Integer orderId = getOne(wrapper).getId();

        myCartItemMap.forEach((key, value)->{
            OrderItem orderItem = new OrderItem(null, orderId, key, value.getProductName(), value.getProductNum(), value.getTotalPrice(),null, orderDTO.getProvince(), orderDTO.getCity(), null, null);
            orderItems.add(orderItem);
        });

        //普通mapper代理类只能一个一个的新增
        for (OrderItem orderItem : orderItems) {
            orderItemMapper.insert(orderItem);
        }


        return MyRes.success(ResEnum.PAY_SUCCESS);
    }

    @Override
    public MyRes getAllMyOrderVOs(Integer userId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<Order> orders = list(wrapper);
        List<OrderVO> orderVOs = BeanCopyUtil.copyBeanList(orders, OrderVO.class);
        orderVOs.forEach((orderVO)->{
            QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_id", orderVO.getId());
            List<OrderItem> orderItems = orderItemMapper.selectList(queryWrapper);
            //给每一个orderItem的图片url属性赋值
            List<Product> products = productMapper.selectList(null);
            outer : for (OrderItem orderItem : orderItems) {
                for (Product product : products) {
                    if (orderItem.getProductId().equals(product.getId())){
                        orderItem.setImageUrl(product.getImageUrl());
                        continue outer;
                    }
                }
            }
            List<OrderItemVO> orderItemVOs = BeanCopyUtil.copyBeanList(orderItems, OrderItemVO.class);
            orderVO.setOrderItems(orderItemVOs);
        });

        return MyRes.success(ResEnum.QUERY_SUCCESS, orderVOs);
    }

    @Override
    public MyRes evaluateProduct(ProductEvaluation productEvaluation) {
        //先将对应订单的订单项设置为已评价
        UpdateWrapper<OrderItem> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_id", productEvaluation.getOrderId())
                        .eq("product_id", productEvaluation.getProductId())
                                .set("is_evaluated", 1);
        orderItemMapper.update(updateWrapper);
        //将评价信息添加到对应的表中
        productEvaluation.setUsername(userInfoMapper.getUsernameById(productEvaluation.getUserId()));
        productEvaluationMapper.insert(productEvaluation);
        return MyRes.success(ResEnum.EVALUATE_PRODUCT_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MyRes delOrder(Integer orderId) {
        //一张d_order表，一张order_item表
        removeById(orderId);

        //此表先不做删除，因为要做数据统计，否则会影响。
        /*QueryWrapper<OrderItem> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", orderId);
        orderItemMapper.delete(wrapper);*/
        return MyRes.success(ResEnum.DEL_ORDER_SUC);
    }

    //封装一个生成随机订单号的方法
    public String genOrderNo(){
        return null;
    }
}
