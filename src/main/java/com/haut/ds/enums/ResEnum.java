package com.haut.ds.enums;

public enum ResEnum {
    LOGIN_SUCCESS(200,"登录成功"),
    LOGIN_ERROR(401,"登录失败"),
    TOKEN_CHECK_SUCCESS(200,"token验证成功"),
    REGISTER_SUCCESS(200,"用户注册成功"),
    REGISTER_ERROR(501,"注册失败"),
    QUERY_SUCCESS(200,"查询成功"),
    QUERY_ERROR(501,"查询失败，请联系后台"),
    LOGOUT_SUCCESS(200, "退出成功"),
    LOGOUT_ERROR(501,"退出失败"),
    ADD_TO_MY_CART_SUCCESS(200, "添加到我的购物车成功"),
    ADD_TO_MY_CART_ERROR(501, "添加到我的购物车失败"),
    REDUCE_CART_ITEM_NUM_SUCCESS(200,"减少购物车项的商品数量成功"),
    REDUCE_CART_ITEM_NUM_ERROR(501,"减少购物车项的商品数量失败"),
    DELETE_CART_ITEM_SUCCESS(200,"删除购物车项商品成功"),
    PLUS_CART_ITEM_NUM_SUCCESS(200, "增加购物车项的商品数量成功"),
    CLEAR_MY_CART_SUCCESS(200, "清空购物车成功"),
    PAY_SUCCESS(200, "用户支付成功"),
    PAY_ERROR(501, "用户支付失败"),
    EVALUATE_PRODUCT_SUCCESS(200, "商品评价成功"),
    MODIFY_USER_INFO_SUC(200, "修改用户个人信息成功"),
    DEL_ORDER_SUC(200, "删除订单成功")
    ;


    Integer code;
    String msg;

    ResEnum() {
    }

    ResEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
