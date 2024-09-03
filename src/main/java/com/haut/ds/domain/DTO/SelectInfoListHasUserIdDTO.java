package com.haut.ds.domain.DTO;

import com.haut.ds.domain.VO.SelectedProductInfoVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SelectInfoListHasUserIdDTO {
    private Integer userId;
    private List<SelectedProductInfoVo> selectInfoList;
}
