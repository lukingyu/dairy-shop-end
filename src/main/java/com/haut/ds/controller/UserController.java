package com.haut.ds.controller;

import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.domain.entity.UserInfo;
import com.haut.ds.domain.entity.UserLogin;
import com.haut.ds.enums.ResEnum;
import com.haut.ds.service.UserLoginService;
import com.haut.ds.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserLoginService userLoginService;


    @PostMapping("/login")
    public MyRes login(@RequestBody UserLogin userLogin){
        return userLoginService.login(userLogin);
    }

    @PostMapping("/checkToken")
    public MyRes checkToken(@RequestBody String tokenStr){
        boolean flag = JwtUtil.checkToken(tokenStr);
        return MyRes.error(ResEnum.TOKEN_CHECK_SUCCESS, flag);
    }

    @PostMapping("/logout/{userId}")
    public MyRes logout(@PathVariable("userId") Integer userId){
        return userLoginService.logout(userId);
    }

    @PostMapping("/register")
    public MyRes register(@RequestBody UserLogin userLogin){
        return userLoginService.register(userLogin);
    }

    @GetMapping("/getUsernameById/{userId}")
    public MyRes getUsernameById(@PathVariable("userId") Integer userId){
        return userLoginService.getUsernameById(userId);
    }

    @GetMapping("/getUserInfo/{userId}")
    public MyRes getUserInfo(@PathVariable("userId") Integer userId){
        return userLoginService.getUserInfo(userId);
    }

    @PostMapping("/modifyUserInfo")
    public MyRes modifyUserInfo(@RequestBody UserInfo userInfo){
        return userLoginService.modifyUserInfo(userInfo);
    }

    @PostMapping("/usernameExist/{username}")
    public MyRes usernameExist(@PathVariable("username") String username){
        return userLoginService.usernameExist(username);
    }

    //用户可视化分析，接口。（此用户最近五天的购买数量趋势）
    @GetMapping("/getBuyTimeAndNumList/{userId}/{limit}")
    public MyRes getBuyTimeAndNumList(@PathVariable("userId") Integer userId,@PathVariable("limit") Integer limit){
        return userLoginService.getBuyTimeAndNumList(userId, limit);
    }

    //用户可视化分析，接口。（此用户品牌购买数量，相当于品牌喜爱度）
    @GetMapping("/getBrandAndBuySumList/{userId}")
    public MyRes getBrandAndBuySumList(@PathVariable("userId") Integer userId){
        return userLoginService.getBrandAndBuySumList(userId);
    }

    //用户可视化分析，接口。（此用户对它已购商品的平均评价分数）
    @GetMapping("/getAvgGradeOfCurUser/{userId}")
    public MyRes getAvgGradeOfCurUser(@PathVariable("userId") Integer userId){
        return userLoginService.getAvgGradeOfCurUser(userId);
    }

    @GetMapping("/getCategoryBuySumByBrandList/{brandId}/{userId}")
    public MyRes getCategoryBuySumByBrandList(@PathVariable("brandId") Integer brandId,@PathVariable("userId")Integer userId){
        return userLoginService.getCategoryBuySumByBrandList(brandId, userId);
    }

}
