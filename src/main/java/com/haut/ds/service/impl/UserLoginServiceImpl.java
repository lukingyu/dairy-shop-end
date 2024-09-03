package com.haut.ds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haut.ds.domain.VO.BrandAndBuySum;
import com.haut.ds.domain.VO.BuyTimeAndNum;
import com.haut.ds.domain.VO.CategoryBuySumByBrand;
import com.haut.ds.domain.VO.LoginVo;
import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.domain.entity.UserInfo;
import com.haut.ds.domain.entity.UserLogin;
import com.haut.ds.enums.ResEnum;
import com.haut.ds.mapper.BrandAndBuySumMapper;
import com.haut.ds.mapper.BuyTimeAndNumMapper;
import com.haut.ds.mapper.UserInfoMapper;
import com.haut.ds.mapper.UserLoginMapper;
import com.haut.ds.service.UserLoginService;
import com.haut.ds.utils.JwtUtil;
import com.haut.ds.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Service("userLoginService")
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements UserLoginService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BuyTimeAndNumMapper buyTimeAndNumMapper;
    @Autowired
    private BrandAndBuySumMapper brandAndBuySumMapper;

    @Override
    public MyRes login(UserLogin userLogin) {
        LambdaQueryWrapper<UserLogin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserLogin::getUsername, userLogin.getUsername())
                        .eq(UserLogin::getPassword, userLogin.getPassword());
        UserLogin one = getOne(wrapper);
        if (!ObjectUtils.isEmpty(one)){
            //登录成功
            String jwtStr = JwtUtil.generateJwtStr(userLogin.getUsername());
            //存取一个键值对到redis中，证明该用户已经在登录状态。消失代表该用户下线了。
            redisUtil.set("username",userLogin.getUsername(),60*60*24*7);
            LoginVo loginVo = new LoginVo(one.getId(),jwtStr);
            return MyRes.success(ResEnum.LOGIN_SUCCESS,loginVo);
        }else{
            //登录失败
            redisUtil.del("username");
            return MyRes.error(ResEnum.LOGIN_ERROR,null);
        }

    }

    @Override
    public MyRes register(UserLogin userLogin) {
        try {
            save(userLogin);
            Integer userId = getOne(new QueryWrapper<UserLogin>().eq("username",userLogin.getUsername())).getId();
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setUsername(userLogin.getUsername());
            userInfoMapper.insert(userInfo);
        } catch (Exception e) {
            return MyRes.error(ResEnum.REGISTER_ERROR);
        }
        return MyRes.success(ResEnum.REGISTER_SUCCESS);
    }

    @Override
    public MyRes getUsernameById(Integer userId) {
        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        wrapper.eq("id",userId);
        UserLogin one = getOne(wrapper);
        return MyRes.success(ResEnum.QUERY_SUCCESS, one.getUsername());
    }

    @Override
    public MyRes logout(Integer userId) {
        UserLogin userLogin = getById(userId);
//        System.out.println(redisUtil.get("username"));
        if(null == redisUtil.get("username")){
            return MyRes.success(ResEnum.LOGOUT_SUCCESS);
        }
        if (redisUtil.get("username").toString().equals(userLogin.getUsername())){
            //才进行退出操作
            redisUtil.del("username");
            return MyRes.success(ResEnum.LOGOUT_SUCCESS);
        }
        return MyRes.error(ResEnum.LOGOUT_ERROR);
    }

    @Override
    public MyRes getUserInfo(Integer userId) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        return MyRes.success(ResEnum.QUERY_SUCCESS, userInfo);
    }

    @Override
    public MyRes modifyUserInfo(UserInfo userInfo) {
        userInfoMapper.updateById(userInfo);
        return MyRes.success(ResEnum.MODIFY_USER_INFO_SUC);
    }

    @Override
    public MyRes usernameExist(String username) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);

        QueryWrapper<UserLogin> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("username", username);
        UserLogin userLogin = getOne(wrapper1);

        if (userInfo != null || userLogin != null){
            //用户名已存在
            return MyRes.error(ResEnum.QUERY_SUCCESS, true);
        }
        return MyRes.success(ResEnum.QUERY_SUCCESS, false);
    }

    @Override
    public MyRes getBuyTimeAndNumList(Integer userId, Integer limit) {
        List<BuyTimeAndNum> buyTimeAndNumList = buyTimeAndNumMapper.getBuyTimeAndNumList(userId, limit);
        if (ObjectUtils.isEmpty(buyTimeAndNumList)){
            return MyRes.success(ResEnum.QUERY_SUCCESS, new ArrayList<BuyTimeAndNum>());
        }
        return MyRes.success(ResEnum.QUERY_SUCCESS, buyTimeAndNumList);
    }

    @Override
    public MyRes getBrandAndBuySumList(Integer userId) {
        List<BrandAndBuySum> brandAndBuySumList = brandAndBuySumMapper.getBrandAndBuySumList(userId);
        if (ObjectUtils.isEmpty(brandAndBuySumList)){
            return MyRes.success(ResEnum.QUERY_SUCCESS, new ArrayList<BrandAndBuySum>());
        }
        return MyRes.success(ResEnum.QUERY_SUCCESS, brandAndBuySumList);
    }

    @Override
    public MyRes getAvgGradeOfCurUser(Integer userId) {
        Double avgGradeOfCurUser = userInfoMapper.getAvgGradeOfCurUser(userId);
        if (null == avgGradeOfCurUser){
            //说明该用户没有评价过。先给个0分
            return MyRes.success(ResEnum.QUERY_SUCCESS, 0.00);
        }
        BigDecimal bigDecimal = new BigDecimal(avgGradeOfCurUser).setScale(2, RoundingMode.HALF_UP);
        return MyRes.success(ResEnum.QUERY_SUCCESS, bigDecimal.doubleValue());
    }

    @Override
    public MyRes getCategoryBuySumByBrandList(Integer brandId, Integer userId) {
        List<CategoryBuySumByBrand> categoryBuySumByBrandList = userInfoMapper.getCategoryBuySumByBrandList(brandId, userId);
        return MyRes.success(ResEnum.QUERY_SUCCESS, categoryBuySumByBrandList);
    }
}
