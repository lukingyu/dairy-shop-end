package com.haut.ds.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryNameAndIds {

    private String categoryName;
    private List<Integer> ids;
}
