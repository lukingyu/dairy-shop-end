package com.haut.ds.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private Integer userId;
    private Map<Integer, MyCartItemMapValueDTO> myCartItemMap;
    private Double needPayPrice;
    private String province;
    private String city;

    /*
    OrderDTO(
        userId=1,
        myCartItemMap={
            1={id=14, userId=1, productName=蒙牛纯牛奶20盒装, productNum=2, totalPrice=101, unitPrice=50.5},
            2={id=15, userId=1, productName=光明莫斯利安酸奶16盒装, productNum=3, totalPrice=195, unitPrice=65},
            3={id=16, userId=1, productName=蒙牛三明治精品装1个, productNum=1, totalPrice=13.5, unitPrice=13.5},
            4={id=17, userId=1, productName=蒙牛学生高钙高锌奶粉400g装, productNum=1, totalPrice=35, unitPrice=35},
            6={id=18, userId=1, productName=君乐宝幼儿配方奶粉800g装, productNum=1, totalPrice=60.3, unitPrice=60.3}
        },
        needPayPrice=404.8,
        province=河南,
        city=郑州)

    */

    /*
    OrderDTO(
        userId=1,
        myCartItemMap={
            1=MyCartItemMapValueDTO(userId=1, productName=蒙牛纯牛奶20盒装, productNum=1, totalPrice=50.5),
            2=MyCartItemMapValueDTO(userId=1, productName=光明莫斯利安酸奶16盒装, productNum=3, totalPrice=195.0),
            3=MyCartItemMapValueDTO(userId=1, productName=蒙牛三明治精品装1个, productNum=1, totalPrice=13.5),
            4=MyCartItemMapValueDTO(userId=1, productName=蒙牛学生高钙高锌奶粉400g装, productNum=1, totalPrice=35.0),
            6=MyCartItemMapValueDTO(userId=1, productName=君乐宝幼儿配方奶粉800g装, productNum=1, totalPrice=60.3)
        },
        needPayPrice=354.3,
        province=河南,
        city=郑州
    )

    */

}
