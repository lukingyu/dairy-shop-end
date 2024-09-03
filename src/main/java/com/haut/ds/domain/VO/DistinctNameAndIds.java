package com.haut.ds.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistinctNameAndIds {

    private String categoryName;
    private String idsStr; //这是一个用逗号分隔的id字符串，比如 "1,2,4,5"
}
