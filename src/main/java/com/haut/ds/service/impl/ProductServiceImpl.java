package com.haut.ds.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haut.ds.domain.DTO.SelectInfoListHasUserIdDTO;
import com.haut.ds.domain.VO.CategoryNameAndIds;
import com.haut.ds.domain.VO.DistinctNameAndIds;
import com.haut.ds.domain.VO.ProductDetailVO;
import com.haut.ds.domain.VO.SelectedProductInfoVo;
import com.haut.ds.domain.entity.*;
import com.haut.ds.enums.ResEnum;
import com.haut.ds.mapper.*;
import com.haut.ds.service.CartItemService;
import com.haut.ds.service.ProductService;
import com.haut.ds.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

//    @Autowired
//    private CartItemMapper cartItemMapper;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductDetailMapper productDetailMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductEvaluationMapper productEvaluationMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public MyRes getProductsByKeyword(String keyword) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Product::getProductName, keyword);
        List<Product> products = list(wrapper);
        return MyRes.success(ResEnum.QUERY_SUCCESS, products);
    }

    @Override
    public MyRes getAllProducts() {
        List<Product> products = list();
        return MyRes.success(ResEnum.QUERY_SUCCESS,products);
    }

    @Override
    public MyRes getSelectedProductInfos(Map<Integer, Integer> selectedProductMap) {
        List<SelectedProductInfoVo> selectedProductInfoVos = new ArrayList<>();

        Set<Integer> keys = selectedProductMap.keySet();
        List<Product> products = listByIds(keys);
        products.forEach((product)->{
            Integer num = selectedProductMap.get(product.getId());
            Double totalPrice = num * product.getPrice();
            SelectedProductInfoVo selectedProductInfoVo = new SelectedProductInfoVo(product.getId(), product.getProductName(), num, totalPrice, product.getImageUrl());
            selectedProductInfoVos.add(selectedProductInfoVo);
        });
        return MyRes.success(ResEnum.QUERY_SUCCESS, selectedProductInfoVos);
    }

    @Override
    public MyRes addToMyCart(SelectInfoListHasUserIdDTO dto) {
        System.out.println(dto);
        //userId=1, selectInfoList=[SelectedProductInfoVo(productId=2, productName=光明莫斯利安酸奶16盒装, num=1, totalPrice=65.0)]
        Integer userId = dto.getUserId();
        List<SelectedProductInfoVo> selectInfoList = dto.getSelectInfoList();
        LambdaQueryWrapper<CartItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartItem::getUserId, userId);
        List<CartItem> allCartItemsOfTheUser = cartItemService.list(wrapper);


        List<CartItem> cartItemsForSave = new ArrayList<>();
        List<CartItem> cartItemsForUpdate = new ArrayList<>();

        outer:for (SelectedProductInfoVo infoVo : selectInfoList) {
            for (CartItem cartItem : allCartItemsOfTheUser) {
                if (cartItem.getProductId().equals(infoVo.getProductId())){
                    //商品id相同，只加数量和价格即可，意思是只更新数据
                    CartItem cartItem1 = new CartItem(null, userId, null, cartItem.getProductNum()+infoVo.getNum(), infoVo.getProductId(), cartItem.getTotalPrice()+ infoVo.getTotalPrice());
                    cartItemsForUpdate.add(cartItem1);
                    continue outer;
                }
                /*//商品id不同，说明该用户第一将该商品加入他的购物车，所以是新增
                CartItem cartItem2 = new CartItem(null, userId, infoVo.getProductName(), infoVo.getNum(), infoVo.getProductId(), infoVo.getTotalPrice());
                cartItemsForSave.add(cartItem2);*/
            }
            //这里必须跳出指定的循环，因为我下面的代码只想在上面这个内for循环里面的if一次都没执行的时候执行
            //可以使用循环标记，来使用continue或者break
            //商品id不同，说明该用户第一将该商品加入他的购物车，所以是新增
            CartItem cartItem = new CartItem(null, userId, infoVo.getProductName(), infoVo.getNum(), infoVo.getProductId(), infoVo.getTotalPrice());
            cartItemsForSave.add(cartItem);

        }
        //用于更新
        for (CartItem cartItem : cartItemsForUpdate) {
            LambdaQueryWrapper<CartItem> wrapper1 = new LambdaQueryWrapper<CartItem>();
            wrapper1.eq(CartItem::getUserId, cartItem.getUserId())
                            .eq(CartItem::getProductId, cartItem.getProductId());
            cartItemService.update(cartItem, wrapper1);
        }
        //用于新增
        cartItemService.saveBatch(cartItemsForSave);
        return MyRes.success(ResEnum.ADD_TO_MY_CART_SUCCESS);
    }

    @Override
    public MyRes getAllBrands() {
        //获取所有的品牌brand
        List<Brand> brands = brandMapper.selectList(null);
        return MyRes.success(ResEnum.QUERY_SUCCESS, brands);
    }

    @Override
    public MyRes getAllCategories() {
        /*//获取所有的分类category
        List<Category> categories = categoryMapper.selectList(null);
        return MyRes.success(ResEnum.QUERY_SUCCESS, categories);*/
        //以上是原始代码。需要时请复原。

        List<DistinctNameAndIds> distinctNameAndIdsList = categoryMapper.getDistinctNameAndIdsList();
        //List<CategoryNameAndIds> categoryNameAndIdsList = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();
        for (DistinctNameAndIds obj : distinctNameAndIdsList) {
            List<Integer> idList = new ArrayList<>();
            if (obj.getIdsStr().contains(",")){
                //说明是多个id，使用,分隔的字符串
                String[] ids = obj.getIdsStr().split(",");
                for (String id : ids) {
                    idList.add(Integer.parseInt(id));
                }
            }else {
                idList.add(Integer.parseInt(obj.getIdsStr()));
            }
            //categoryNameAndIdsList.add(new CategoryNameAndIds(obj.getCategoryName(), idList));
            map.put(obj.getCategoryName(), idList);
        }
        return MyRes.success(ResEnum.QUERY_SUCCESS, map);
    }

    @Override
    public MyRes getProductDetail(Integer productId) {
        QueryWrapper<ProductDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        ProductDetail productDetail = productDetailMapper.selectOne(queryWrapper);

        //获取该商品总销量
        Integer totalSalesVolume = 0;
        QueryWrapper<OrderItem> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("product_id", productId);
        List<OrderItem> orderItemsOfTheProduct = orderItemMapper.selectList(queryWrapper2);
        if (! orderItemsOfTheProduct.isEmpty()){
            //如果不是空，说明有销量，才给销量赋值
            for (OrderItem orderItem : orderItemsOfTheProduct) {
                totalSalesVolume += orderItem.getProductNum();
            }
        }

        //获取该商品的所有评价
        QueryWrapper<ProductEvaluation> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("product_id", productId).orderByDesc("create_time");
        List<ProductEvaluation> productEvaluations = productEvaluationMapper.selectList(queryWrapper3);


        //获取最新的一条评价
        ProductEvaluation latestProductEvaluation = productEvaluationMapper.getLatestProductEvaluation(productId);

        //给VO赋值
        ProductDetailVO productDetailVO = BeanCopyUtil.copyBean(productDetail, ProductDetailVO.class);
        productDetailVO.setTotalSalesVolume(totalSalesVolume);
        if (productEvaluations!=null){
            productDetailVO.setProductEvaluations(productEvaluations);
        }else {
            productDetailVO.setProductEvaluations(new ArrayList<>());
        }

        productDetailVO.setLatestProductEvaluation(latestProductEvaluation);
        productDetailVO.setCategoryName(categoryMapper.getCategoryName(productDetail.getCategoryId()));

        return MyRes.success(ResEnum.QUERY_SUCCESS, productDetailVO);
    }

    @Override
    public MyRes getFavoriteProducts(Integer userId) {
        List<Integer> orderIds = getBaseMapper().getOrderIdsByUserId(userId);
        System.out.println("该用户的订单号是：" + orderIds);
        if (orderIds.size() != 0){
            //订单Id不为空，说明该用户之前买过商品。
            List<Integer> favoriteCategoryIds = orderItemMapper.getFavoriteCategoryIds(orderIds);
            HashSet<Integer> fcIdSet = new HashSet<>();
            fcIdSet.addAll(favoriteCategoryIds);

            List<Product> tempProducts = list(new QueryWrapper<Product>().in("category_id", fcIdSet));
            if (tempProducts.size() >= 10){
                //如果超过10个，直接随机取出。
                Set<Product> favoriteProducts = RandomUtil.randomEleSet(tempProducts, 10);
                return MyRes.success(ResEnum.QUERY_SUCCESS, favoriteProducts);
            }

            //走到这里，说明根据种类查出的商品不够10个，则从其它商品中补够
            List<Integer> hadProIds = new ArrayList<>(); //已有的商品Id，不需要再次随机筛选出
            tempProducts.forEach((pro) -> {
                hadProIds.add(pro.getId());
            });
            Set<Product> supplyProducts = RandomUtil.randomEleSet(list(new QueryWrapper<Product>().notIn("id", hadProIds)), 10-hadProIds.size());
            tempProducts.addAll(supplyProducts);
            return MyRes.success(ResEnum.QUERY_SUCCESS, tempProducts);
        }else {
            //这里说明该用户没有购买过任何商品
            //则随机推荐10件商品
            Set<Product> random10Products = RandomUtil.randomEleSet(list(), 10);
            return MyRes.success(ResEnum.QUERY_SUCCESS, random10Products);
        }

    }
}
