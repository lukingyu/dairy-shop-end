package com.haut.ds.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderVO {
    private Integer id;
    private String orderNo;
    private Double allPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private String province;
    private String city;
    private List<OrderItemVO> orderItems;
}
