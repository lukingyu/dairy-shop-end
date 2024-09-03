package com.haut.ds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haut.ds.domain.entity.MyRes;
import com.haut.ds.domain.entity.UserInfo;
import com.haut.ds.domain.entity.UserLogin;

public interface UserLoginService extends IService<UserLogin> {
    MyRes login(UserLogin userLogin);

    MyRes register(UserLogin userLogin);

    MyRes getUsernameById(Integer userId);

    MyRes logout(Integer userId);

    MyRes getUserInfo(Integer userId);

    MyRes modifyUserInfo(UserInfo userInfo);

    MyRes usernameExist(String username);

    MyRes getBuyTimeAndNumList(Integer userId, Integer limit);

    MyRes getBrandAndBuySumList(Integer userId);

    MyRes getAvgGradeOfCurUser(Integer userId);

    MyRes getCategoryBuySumByBrandList(Integer brandId, Integer userId);
}
