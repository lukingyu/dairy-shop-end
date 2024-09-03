package com.haut.ds.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtil {

    private BeanCopyUtil(){}

    //单个实体类拷贝（借助Spring的BeanUtils工具类，进行二次封装）
    public static <V> V copyBean(Object source, Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性拷贝
            BeanUtils.copyProperties(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //数组拷贝（借助Spring的BeanUtils工具类，自己手动封装一个能拷贝数组的）
    public static  <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz){

        List<V> result = list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
        return result;
    }
}
