package com.haut.ds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haut.ds.domain.DTO.CartItemDTO;
import com.haut.ds.domain.VO.CartItemVO;
import com.haut.ds.domain.entity.CartItem;
import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.domain.entity.Product;
import com.haut.ds.enums.ResEnum;
import com.haut.ds.mapper.CartItemMapper;
import com.haut.ds.mapper.ProductMapper;
import com.haut.ds.service.CartItemService;
import com.haut.ds.service.ProductService;
import com.haut.ds.utils.BeanCopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements CartItemService {

    //循环依赖了，先不用这个
//    @Autowired
//    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public MyRes getAllMyCartItems(Integer userId) {
        List<CartItemVO> myCartItemVOs = null;
        List<CartItem> myCartItems = null;
        //一会往里存id们，为的是获取单价（因为我表设计的不好）
        List<Integer> productIds = new ArrayList<>();
        try {
            QueryWrapper<CartItem> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId);
            myCartItems = list(wrapper);
            if (ObjectUtils.isEmpty(myCartItems)){
                return MyRes.success(ResEnum.QUERY_SUCCESS, null);
            }
            for (CartItem item : myCartItems) {
                productIds.add(item.getProductId());
            }
            List<Product> products = productMapper.selectBatchIds(productIds);

//            BeanUtils.copyProperties(myCartItems, myCartItemVOs); //错误的，这个工具类只能拷贝普通JavaBean
            myCartItemVOs = BeanCopyUtil.copyBeanList(myCartItems, CartItemVO.class);

            outer : for (CartItemVO itemVO : myCartItemVOs) {
                for (Product product : products) {
                    if (itemVO.getProductId().equals(product.getId())){
                        itemVO.setUnitPrice(product.getPrice());
                        continue outer;
                    }
                }
            }
        } catch (Exception e) {
            return MyRes.error(ResEnum.QUERY_ERROR);
        }
        //myCartItemVOs和原来的myCartItems几乎一致，只是每一个元素多了一个单价属性
        return MyRes.success(ResEnum.QUERY_SUCCESS, myCartItemVOs);
    }

    @Override
    public MyRes reduceCartItemProductNum(CartItemDTO cartItemDTO) {
        Double unitPrice = cartItemDTO.getUnitPrice();
        //CartItem cartItem = BeanCopyUtil.copyBean(cartItemDTO, CartItem.class);
        LambdaQueryWrapper<CartItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartItem::getUserId, cartItemDTO.getUserId())
                        .eq(CartItem::getProductId, cartItemDTO.getProductId());
        CartItem cartItem = cartItemMapper.selectOne(wrapper);
        //减数量
        cartItem.setProductNum(cartItem.getProductNum() - 1);
        //减总价
        cartItem.setTotalPrice(cartItem.getTotalPrice() - cartItemDTO.getUnitPrice());
        //更新数据库
        cartItemMapper.updateById(cartItem);
        return MyRes.success(ResEnum.REDUCE_CART_ITEM_NUM_SUCCESS);
    }

    @Override
    public MyRes deleteCartItemProduct(CartItemDTO cartItemDTO) {
        LambdaQueryWrapper<CartItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartItem::getUserId, cartItemDTO.getUserId())
                        .eq(CartItem::getProductId, cartItemDTO.getProductId());
        cartItemMapper.delete(wrapper);
        return MyRes.success(ResEnum.DELETE_CART_ITEM_SUCCESS);
    }

    @Override
    public MyRes plusCartItemProductNum(CartItemDTO cartItemDTO) {
        Double unitPrice = cartItemDTO.getUnitPrice();
        LambdaQueryWrapper<CartItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartItem::getUserId, cartItemDTO.getUserId())
                .eq(CartItem::getProductId, cartItemDTO.getProductId());
        CartItem cartItem = cartItemMapper.selectOne(wrapper);
        //加数量
        cartItem.setProductNum(cartItem.getProductNum() + 1);
        //加总价
        cartItem.setTotalPrice(cartItem.getTotalPrice() + cartItemDTO.getUnitPrice());
        //更新数据库
        cartItemMapper.updateById(cartItem);
        return MyRes.success(ResEnum.PLUS_CART_ITEM_NUM_SUCCESS);
    }

    @Override
    public MyRes clearMyCart(CartItemDTO cartItemDTO) {
        LambdaQueryWrapper<CartItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartItem::getUserId, cartItemDTO.getUserId());
        cartItemMapper.delete(wrapper);
        return MyRes.success(ResEnum.CLEAR_MY_CART_SUCCESS);
    }
}
