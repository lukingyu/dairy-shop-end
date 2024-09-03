package com.haut.ds.domain.entity;

import com.haut.ds.enums.ResEnum;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MyRes {

    private Integer code;
    private String msg;
    private Object data;

    private MyRes(){}

    private MyRes(Integer code){
        this.code = code;
    }
    private MyRes(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public MyRes(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public MyRes(ResEnum resEnum, Object data){
        this.code = resEnum.getCode();
        this.msg = resEnum.getMsg();
        if (data != null){
            this.data = data;
        }
    }

    public static MyRes success(ResEnum resEnum,Object data){
        return new MyRes(resEnum,data);
    }
    public static MyRes success(ResEnum resEnum){
        return new MyRes(resEnum,null);
    }

    public static MyRes error(ResEnum resEnum,Object data){
        return new MyRes(resEnum,data);
    }
    public static MyRes error(ResEnum resEnum){
        return new MyRes(resEnum,null);
    }



}
