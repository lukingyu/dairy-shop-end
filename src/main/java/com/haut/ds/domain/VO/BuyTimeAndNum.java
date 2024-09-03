package com.haut.ds.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyTimeAndNum { //个人购买日期，以及当天购买数量。（主要用于可视化使用）

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date buyTime; //购买时间（个人）

    private Integer buyProNum;  //当天购买的商品数量
}
